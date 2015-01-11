package mx.ipn.cfie.proyectos.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.*;

/**
 * User: sg
 * Date: 29/09/12
 * Time: 16:40
 */
@Entity
public class Proyecto {
	private String nivelAcademico;
	private String unidadAcademica;
	private String departamento;
	private String tipoProyecto;
	private String plazo;
	private String folioSIP;
	private String titulo;
	private String tipoInvestigacion;
	private String areaConocimiento;
	private String sector;
	private String subsector;
	private String resumen;
	private String objetivos;
	private String productoFinal;
	private Set<String> subproductos;
	private int financiamientoExterno;
	private Double cantidad;
	private Set<Actividad> actividades;
	private List<Presupuesto> presupuestos;
	private Evaluacion evaluacion;
	private Usuario evaluador;
	private Usuario director;
	private Alumno alumno1;
	private Alumno alumno2;
	private Alumno alumno3;
	private Alumno alumno4;
	private Alumno alumno5;
	private Investigador investigador1;
	private Investigador investigador2;
	private boolean evaluado;
	private Date fecha;

	public Proyecto() {
		actividades = new HashSet<Actividad>();
		presupuestos = new ArrayList<Presupuesto>();
		evaluacion = new Evaluacion();
		subproductos = new HashSet<String> ();
		evaluado = false;
		alumno1 = new Alumno();
		alumno2 = new Alumno();
		alumno3 = new Alumno();
		alumno4 = new Alumno();
		alumno5 = new Alumno();
		investigador1 = new Investigador();
		investigador2 = new Investigador();
	}

	@Id
	@GenericGenerator(name="seq_id", strategy="mx.ipn.cfie.proyectos.generators.FolioGenerator")
	@GeneratedValue(generator="seq_id")
	public String getFolioSIP() {
		return folioSIP;
	}

	public void setFolioSIP(String folioSIP) {
		this.folioSIP = folioSIP;
	}

	public String getNivelAcademico() {
		return nivelAcademico;
	}

	public void setNivelAcademico(String nivelAcademico) {
		this.nivelAcademico = nivelAcademico;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getTipoInvestigacion() {
		return tipoInvestigacion;
	}

	public void setTipoInvestigacion(String tipoInvestigacion) {
		this.tipoInvestigacion = tipoInvestigacion;
	}

	public String getAreaConocimiento() {
		return areaConocimiento;
	}

	public void setAreaConocimiento(String areaConocimiento) {
		this.areaConocimiento = areaConocimiento;
	}

	public String getResumen() {
		return resumen;
	}

	public void setResumen(String resumen) {
		this.resumen = resumen;
	}

	public String getObjetivos() {
		return objetivos;
	}

	public void setObjetivos(String objetivos) {
		this.objetivos = objetivos;
	}

	public int getFinanciamientoExterno() {
		return financiamientoExterno;
	}

	public void setFinanciamientoExterno(int financiamientoExterno) {
		this.financiamientoExterno = financiamientoExterno;
	}

	public Double getCantidad() {
		return cantidad;
	}

	public void setCantidad(Double cantidad) {
		this.cantidad = cantidad;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	public Set<Actividad> getActividades() {
		return actividades;
	}

	public void setActividades(Set<Actividad> actividades) {
		this.actividades = actividades;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	public List<Presupuesto> getPresupuestos() {
		return presupuestos;
	}

	public void setPresupuestos(List<Presupuesto> presupuestos) {
		this.presupuestos = presupuestos;
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

	public String getTipoProyecto() {
		return tipoProyecto;
	}

	public void setTipoProyecto(String tipoProyecto) {
		this.tipoProyecto = tipoProyecto;
	}

	public String getPlazo() {
		return plazo;
	}

	public void setPlazo(String plazo) {
		this.plazo = plazo;
	}

	public String getSector() {
		return sector;
	}

	public void setSector(String sector) {
		this.sector = sector;
	}

	public String getSubsector() {
		return subsector;
	}

	public void setSubsector(String subsector) {
		this.subsector = subsector;
	}

	public String getProductoFinal() {
		return productoFinal;
	}

	public void setProductoFinal(String productoFinal) {
		this.productoFinal = productoFinal;
	}

	@ElementCollection(fetch = FetchType.EAGER)
	public Set<String> getSubproductos() {
		return subproductos;
	}

	public void setSubproductos(Set<String> subproductos) {
		this.subproductos = subproductos;
	}

	@OneToOne(cascade = CascadeType.ALL)
	public Evaluacion getEvaluacion() {
		return evaluacion;
	}

	public void setEvaluacion(Evaluacion evaluacion) {
		this.evaluacion = evaluacion;
	}

	@ManyToOne
	public Usuario getEvaluador() {
		return evaluador;
	}

	public void setEvaluador(Usuario evaluador) {
		this.evaluador = evaluador;
	}

	@ManyToOne
	public Usuario getDirector() {
		return director;
	}

	public void setDirector(Usuario director) {
		this.director = director;
	}

	@ManyToOne(cascade = CascadeType.ALL )
	public Alumno getAlumno1() {
		return alumno1;
	}

	public void setAlumno1(Alumno alumno1) {
		this.alumno1 = alumno1;
	}

	@ManyToOne(cascade = CascadeType.ALL )
	public Alumno getAlumno2() {
		return alumno2;
	}

	public void setAlumno2(Alumno alumno2) {
		this.alumno2 = alumno2;
	}

	@ManyToOne(cascade = CascadeType.ALL )
	public Alumno getAlumno3() {
		return alumno3;
	}

	public void setAlumno3(Alumno alumno3) {
		this.alumno3 = alumno3;
	}

	@ManyToOne(cascade = CascadeType.ALL )
	public Alumno getAlumno4() {
		return alumno4;
	}

	public void setAlumno4(Alumno alumno4) {
		this.alumno4 = alumno4;
	}

	@ManyToOne(cascade = CascadeType.ALL )
	public Alumno getAlumno5() {
		return alumno5;
	}

	public void setAlumno5(Alumno alumno5) {
		this.alumno5 = alumno5;
	}

	@ManyToOne(cascade = CascadeType.ALL )
	public Investigador getInvestigador1() {
		return investigador1;
	}

	public void setInvestigador1(Investigador investigador1) {
		this.investigador1 = investigador1;
	}

	@ManyToOne(cascade = CascadeType.ALL )
	public Investigador getInvestigador2() {
		return investigador2;
	}

	public void setInvestigador2(Investigador investigador2) {
		this.investigador2 = investigador2;
	}

	public boolean isEvaluado() {
		return evaluado;
	}

	public void setEvaluado(boolean evaluado) {
		this.evaluado = evaluado;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

    @Transient
    public Double getTotalPresupuesto(){
        double total = 0.0;
        for( Presupuesto p : getPresupuestos() ){
            total += p.getMonto();
        }
        return total;
    }

	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append("Folio: ").append(folioSIP).append("\n")
				.append("Director: ").append(director).append("\n")
				.append("Alumno 1: ").append(alumno1).append("\n")
				.append("Alumno 2: ").append(alumno2).append("\n")
				.append("Alumno 3: ").append(alumno3).append("\n")
				.append("Alumno 4: ").append(alumno4).append("\n")
				.append("Alumno 5: ").append(alumno5).append("\n")
				.append("Investigador 1: ").append(investigador1).append("\n")
				.append("Investigador 2: ").append(investigador2).append("\n");
		return sb.toString();
	}
}
