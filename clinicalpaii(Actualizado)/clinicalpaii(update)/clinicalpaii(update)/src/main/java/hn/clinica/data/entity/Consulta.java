package hn.clinica.data.entity;

public class Consulta {
	
	 private String Identidad;
     private String Nombre;
     private String Telefono;
     private String Medicamentos;
     private Integer Stock;
     private String Fecha;
	public String getIdentidad() {
		return Identidad;
	}
	public void setIdentidad(String identidad) {
		Identidad = identidad;
	}
	public String getNombre() {
		return Nombre;
	}
	public void setNombre(String nombre) {
		Nombre = nombre;
	}
	public String getTelefono() {
		return Telefono;
	}
	public void setTelefono(String telefono) {
		Telefono = telefono;
	}
	public String getMedicamentos() {
		return Medicamentos;
	}
	public void setMedicamentos(String medicamentos) {
		Medicamentos = medicamentos;
	}
	public Integer getStock() {
		return Stock;
	}
	public void setStock(Integer stock) {
		Stock = stock;
	}
	public String getFecha() {
		return Fecha;
	}
	public void setFecha(String fecha) {
		Fecha = fecha;
	}


}
