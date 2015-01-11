package mx.ipn.cfie.proyectos.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * User: sg
 * Date: 16/09/12
 * Time: 11:29 AM
 */
@Entity
public class Usuario {
	private String username;
	private String password;
	private String nombre;
	private String apellidoPaterno;
	private String apellidoMaterno;
	private String numeroEmpleado;
	private String rfc;
	private String nivelAcademico;
	private String unidadAcademica;
	private String departamento;
	private String puesto;
	private String telefonoOficina;
	private String extension;
	private String movil;
	private String plaza;
	private String beca;
	private String gradoAcademico;
	private boolean sni;
	private String email;
	private Organizacion organizacion;
	
	private String role;

	public Usuario() { }

	@Id
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidoPaterno() {
		return apellidoPaterno;
	}

	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
	}

	public String getApellidoMaterno() {
		return apellidoMaterno;
	}

	public void setApellidoMaterno(String apellidoMaterno) {
		this.apellidoMaterno = apellidoMaterno;
	}

	public String getNumeroEmpleado() {
		return numeroEmpleado;
	}

	public void setNumeroEmpleado(String numeroEmpleado) {
		this.numeroEmpleado = numeroEmpleado;
	}

	public String getRfc() {
		return rfc;
	}

	public void setRfc(String rfc) {
		this.rfc = rfc;
	}

	public String getNivelAcademico() {
		return nivelAcademico;
	}

	public void setNivelAcademico(String nivelAcademico) {
		this.nivelAcademico = nivelAcademico;
	}

	public String getUnidadAcademica() {
		return unidadAcademica;
	}

	public void setUnidadAcademica(String unidadAcademica) {
		this.unidadAcademica = unidadAcademica;
	}

	public String getDepartamento() {
		return departamento;
	}

	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}

	public String getPuesto() {
		return puesto;
	}

	public void setPuesto(String puesto) {
		this.puesto = puesto;
	}

	public String getTelefonoOficina() {
		return telefonoOficina;
	}

	public void setTelefonoOficina(String telefonoOficina) {
		this.telefonoOficina = telefonoOficina;
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	public String getMovil() {
		return movil;
	}

	public void setMovil(String movil) {
		this.movil = movil;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getPlaza() {
		return plaza;
	}

	public void setPlaza(String plaza) {
		this.plaza = plaza;
	}

	public String getBeca() {
		return beca;
	}

	public void setBeca(String beca) {
		this.beca = beca;
	}

	public String getGradoAcademico() {
		return gradoAcademico;
	}

	public void setGradoAcademico(String gradoAcademico) {
		this.gradoAcademico = gradoAcademico;
	}

	public boolean isSni() {
		return sni;
	}

	public void setSni(boolean sni) {
		this.sni = sni;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@ManyToOne
	public Organizacion getOrganizacion() {
		return organizacion;
	}

	public void setOrganizacion(Organizacion organizacion) {
		this.organizacion = organizacion;
	}

	@Override
	public String toString(){
		return this.nombre + " " + this.apellidoPaterno + " " + this.apellidoMaterno;
	}

	@Override
	public boolean equals(Object other){
		if( other instanceof Usuario){
			return this.username.equals(((Usuario)other).getUsername());
		}
		return false;
	}
}
