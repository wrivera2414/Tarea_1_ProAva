package hn.clinica.data.entity;

import jakarta.persistence.Entity;
import java.time.LocalDateTime;

import com.vaadin.flow.component.combobox.ComboBox;

@Entity
public class Citas extends AbstractEntity {

    private Integer idcita;
    private LocalDateTime fecha;
    private ComboBox <String>paciente;
    private String direccion;
    private String telefono;

    public Integer getIdcita() {
        return idcita;
    }
    public void setIdcita(Integer idcita) {
        this.idcita = idcita;
    }
    public LocalDateTime getFecha() {
        return fecha;
    }
    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }
 
    public String getDireccion() {
        return direccion;
    }
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    public String getTelefono() {
        return telefono;
    }
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
	public ComboBox <String> getPaciente() {
		return paciente;
	}
	public void setPaciente(ComboBox <String> paciente) {
		this.paciente = paciente;
	}

}
