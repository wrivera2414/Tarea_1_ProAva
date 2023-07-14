package hn.clinica.views.pacientes;

import com.vaadin.flow.component.UI;

import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.Notification.Position;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.splitlayout.SplitLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.RouteAlias;

import hn.clinica.data.controller.PacientesInteractor;
import hn.clinica.data.controller.PacientesInteractorImpl;
import hn.clinica.data.entity.Pacientes;
import hn.clinica.views.MainLayout;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.orm.ObjectOptimisticLockingFailureException;
import java.util.ArrayList;

@PageTitle("Pacientes")
@Route(value = "pacientes/:pacientesID?/:action?(edit)", layout = MainLayout.class)
@RouteAlias(value = "", layout = MainLayout.class)
public class PacientesView extends Div implements BeforeEnterObserver, PacientesViewModel {

	private static final String PACIENTES_ID = "pacientesID";
	private final String PACIENTES_EDIT_ROUTE_TEMPLATE = "pacientes/%s/edit";
    private final Grid<Pacientes> grid = new Grid<>(Pacientes.class, false);

    private TextField nombre;
    private TextField identidad;
    private TextField telefono;
    private TextField edad;
    private ComboBox<Pacientes> sangre;
    private TextField peso;
    private TextField altura;
    private List<Pacientes> pacientes;
    

    private final Button cancel = new Button("Cancelar");
    private final Button save = new Button("Guardar");
    private Pacientes paciente;
    private PacientesInteractor controlador;

    public PacientesView() {
        addClassNames("pacientes-view");
        pacientes = new ArrayList<>();
        this.controlador = new PacientesInteractorImpl(this);

        // Create UI
        SplitLayout splitLayout = new SplitLayout();
        createGridLayout(splitLayout);
        createEditorLayout(splitLayout);

        add(splitLayout);

        // Configure Grid
        grid.addColumn("nombre").setAutoWidth(true);
        grid.addColumn("identidad").setAutoWidth(true);
        grid.addColumn("telefono").setAutoWidth(true);
        grid.addColumn("edad").setAutoWidth(true);
        grid.addColumn("sangre").setAutoWidth(true);
        grid.addColumn("peso").setAutoWidth(true);
        grid.addColumn("altura").setAutoWidth(true);
        
        
        /*grid.setItems(query -> pacientesService.list(
                PageRequest.of(query.getPage(), query.getPageSize(), VaadinSpringDataHelpers.toSpringDataSort(query)))
                .stream());*/
        
        
        grid.addThemeVariants(GridVariant.LUMO_NO_BORDER);

        // when a row is selected or deselected, populate form
        grid.asSingleSelect().addValueChangeListener(event -> {
            if (event.getValue() != null) {
                UI.getCurrent().navigate(String.format(PACIENTES_EDIT_ROUTE_TEMPLATE, event.getValue().getIdentidad()));
            } else {
                clearForm();
                UI.getCurrent().navigate(PacientesView.class);
            }
        });
        
        //mando a traer los pacientes del repositorio
        this.controlador.consultarPacientes();

        cancel.addClickListener(e -> {
            clearForm();
            refreshGrid();
        });

        save.addClickListener(e -> {
            try {
                if (this.paciente == null) {
                    this.paciente = new Pacientes();
                }
                clearForm();
                refreshGrid();
                Notification.show("Data updated");
                UI.getCurrent().navigate(PacientesView.class);
            } catch (ObjectOptimisticLockingFailureException exception) {
                Notification n = Notification.show(
                        "Error updating the data. Somebody else has updated the record while you were making changes.");
                n.setPosition(Position.MIDDLE);
                n.addThemeVariants(NotificationVariant.LUMO_ERROR);
        }});
    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        Optional<String> pacientesId = event.getRouteParameters().get(PACIENTES_ID);
        boolean encontrado = false;
       if (pacientesId.isPresent()){
        	for (Pacientes e: this.pacientes){
        		if(e.getIdentidad().equals(pacientesId.get()))
        		{
                    populateForm(e);
                    encontrado = true;
                    break;
        		}	
        	}
        		if(!encontrado) 
        		{
        			 Notification.show(String.format("El empleado con identidad= %s", pacientesId.get() + "No fue encontrado"),
                             3000, Notification.Position.BOTTOM_START);
                     refreshGrid();
                     event.forwardTo(PacientesView.class); 
        			
        		}
        	}
    }
    	

    	
    	
    private void createEditorLayout(SplitLayout splitLayout) {
        Div editorLayoutDiv = new Div();
        editorLayoutDiv.setClassName("editor-layout");

        Div editorDiv = new Div();
        editorDiv.setClassName("editor");
        editorLayoutDiv.add(editorDiv);


        FormLayout formLayout = new FormLayout();
        nombre = new TextField("Nombre");
        identidad = new TextField("Identidad");
        telefono = new TextField("Telefono");
        telefono.setPrefixComponent(new Span("+504"));
        edad = new TextField("Edad");
        edad.setSuffixComponent(new Span("Años"));
       
        
        sangre = new ComboBox<>("Sangre");
        Collection<Pacientes> listadoTipoSangre = generarTipoSangre();
        sangre.setItems(listadoTipoSangre);
        sangre.setItemLabelGenerator(Pacientes::getSangre);
  
        
        peso = new TextField("Peso");
        peso.setSuffixComponent(new Span("Lbs"));

        
        altura = new TextField("Altura");
        altura.setSuffixComponent(new Span("Cm"));
        
        
        //Agregar Componentes al Layout
        formLayout.add(nombre, identidad,sangre, edad, peso, altura);

        editorDiv.add(formLayout);
        createButtonLayout(editorLayoutDiv);

        splitLayout.addToSecondary(editorLayoutDiv);
    }


	private Collection<Pacientes> generarTipoSangre() {
		
		
		List<Pacientes> listado = new ArrayList<>();
		Pacientes aPositivo = new Pacientes();
		aPositivo.setSangre("A-POSITIVO");
		
		Pacientes oPositivo = new Pacientes();
		oPositivo.setSangre("O-POSITIVO");
		
		Pacientes oNegativo = new Pacientes();
		oNegativo.setSangre("O-NEGATIVO");
	
		
		listado.add(aPositivo);
		listado.add(oPositivo);
		listado.add(oNegativo);
		
		return listado;
	}

	private void createButtonLayout(Div editorLayoutDiv) {
        HorizontalLayout buttonLayout = new HorizontalLayout();
        buttonLayout.setClassName("button-layout");
        cancel.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        buttonLayout.add(save, cancel);
        editorLayoutDiv.add(buttonLayout);
    }

    private void createGridLayout(SplitLayout splitLayout) {
        Div wrapper = new Div();
        wrapper.setClassName("grid-wrapper");
        splitLayout.addToPrimary(wrapper);
        wrapper.add(grid);
    }

    private void refreshGrid() {
        grid.select(null);
        grid.getDataProvider().refreshAll();
    }

    private void clearForm() {
        populateForm(null);
    }

    private void populateForm(Pacientes value) {
    	if(value == null) 
    	{
    		this.nombre.setValue("");	
		    this.identidad.setValue("");	
		    this.telefono.setValue("");	
		    this.edad.setValue("");	
		    this.sangre.setValue(null);	
		    this.peso.setValue("");	
		    this.altura.setValue("");
    	}
    	else 
    	{
    		this.nombre.setValue(value.getNombre());	
    	    this.identidad.setValue(value.getIdentidad());	
    	    this.telefono.setValue(value.getTelefono());	
    	    this.edad.setValue(value.getEdad().toString());	 
    	    Pacientes seleccionSangre = new Pacientes();
    	    seleccionSangre.setSangre(value.getSangre());
    	    this.sangre.setValue(seleccionSangre);
    	    this.peso.setValue(value.getPeso().toString());	
    	    this.altura.setValue(value.getAltura().toString());	
    	
    	} 	
    	
   }

	@Override
	public void refrescarGridPacientes(List<Pacientes> pacientes) {
		//Este Metodo refresca el Grid
		Collection<Pacientes> items = pacientes;
		grid.setItems(items);
		this.pacientes = pacientes;
		
	}
}
