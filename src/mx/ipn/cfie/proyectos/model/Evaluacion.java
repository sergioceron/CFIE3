package mx.ipn.cfie.proyectos.model;

import javax.persistence.*;

/**
 * User: sg
 * Date: 24/10/12
 * Time: 18:45
 */
@Entity
public class Evaluacion {
	private Long id;
	private int c1;
	private int c2;
	private int c3;
	private int c4;
	private int c5;
	private int c6;
	private int c7;
	private int c8;
	private int c9;
	private int c10;
	private int c11;
	private int c12;
	private int c13;
	private int c14;
	private int c15;
	private String observaciones;

	public Evaluacion() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getC1() {
		return c1;
	}

	public void setC1(int c1) {
		this.c1 = c1;
	}

	public int getC2() {
		return c2;
	}

	public void setC2(int c2) {
		this.c2 = c2;
	}

	public int getC3() {
		return c3;
	}

	public void setC3(int c3) {
		this.c3 = c3;
	}

	public int getC4() {
		return c4;
	}

	public void setC4(int c4) {
		this.c4 = c4;
	}

	public int getC5() {
		return c5;
	}

	public void setC5(int c5) {
		this.c5 = c5;
	}

	public int getC6() {
		return c6;
	}

	public void setC6(int c6) {
		this.c6 = c6;
	}

	public int getC7() {
		return c7;
	}

	public void setC7(int c7) {
		this.c7 = c7;
	}

	public int getC8() {
		return c8;
	}

	public void setC8(int c8) {
		this.c8 = c8;
	}

	public int getC9() {
		return c9;
	}

	public void setC9(int c9) {
		this.c9 = c9;
	}

	public int getC10() {
		return c10;
	}

	public void setC10(int c10) {
		this.c10 = c10;
	}

	public int getC11() {
		return c11;
	}

	public void setC11(int c11) {
		this.c11 = c11;
	}

	public int getC12() {
		return c12;
	}

	public void setC12(int c12) {
		this.c12 = c12;
	}

	public int getC13() {
		return c13;
	}

	public void setC13(int c13) {
		this.c13 = c13;
	}

	public int getC14() {
		return c14;
	}

	public void setC14(int c14) {
		this.c14 = c14;
	}

	public int getC15() {
		return c15;
	}

	public void setC15(int c15) {
		this.c15 = c15;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	
	@Transient
	public int getPuntuacionTotal(){
		return c1 + c2 + c3 + c4 + c5 + c6 + c7 + c8 + c9 +
				c10 + c11 + c12 + c13 + c14 + c15;
	}

    @Transient
    public double getCalificacionTotal(){
        return c1*.05/5 + c2*.04/5 + c3*.05/5 + c4*.09/5 + c5*.09/5 + c6*.04/5 + c7*.07/5 + c8*.09/5 + c9*.09/5 +
                c10*.08/5 + c11*.05/5 + c12*.08/5 + c13*.05/5 + c14*.07/5 + c15*.06/5;
    }

	@Transient
	public String getDictamen(){
		String dictamen = null;
		int puntuacion = (int)(getCalificacionTotal()*100);
		if( puntuacion <= 40 ){
			dictamen = "No Elegible";
		} else if( puntuacion >= 41 && puntuacion <= 69 ){
			dictamen = "Condicionado";
		} else if( puntuacion >= 70 && puntuacion <= 100 ){
			dictamen = "Elegible";
		}
		return dictamen;
	}
}

