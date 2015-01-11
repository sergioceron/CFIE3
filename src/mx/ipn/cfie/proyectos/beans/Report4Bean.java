package mx.ipn.cfie.proyectos.beans;

import mx.ipn.cfie.proyectos.dao.HibernateDao;
import mx.ipn.cfie.proyectos.model.Proyecto;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Order;
import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.PieChartModel;

import javax.annotation.PostConstruct;
import java.util.*;

/**
 * User: sg
 * Date: 7/11/12
 * Time: 15:13
 */
public class Report4Bean {
	static final String MONTHS[] = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};
    static final String C1 = "Ingenieria y Tecnologia";
    static final String C2 = "Ciencias Naturales";
    static final String C3 = "Ciencias Medicas";
    static final String C4 = "Ciencias Agricolas";
    static final String C5 = "Ciencias Sociales";
    static final String C6 = "Humanidades";
    static final String C7 = "Perspectiva de Genero";

	private ConfigBean configBean;
	private BitacoraBean bitacora;
	private SessionBean sessionBean;
    private Map<String, Integer> totalMonths;
    private Map<String, Integer> totalCriterio;

	private List<Proyecto> actual = new ArrayList<Proyecto>();
	List<Proyecto> all = new ArrayList<Proyecto>();
    CartesianChartModel model = new CartesianChartModel();

	public Report4Bean(){

	}

	@PostConstruct
	public void init(){
		HibernateDao dao = new HibernateDao();
        Criteria criteria = dao.getSession().createCriteria(Proyecto.class);
        criteria.setFetchMode("subproductos", FetchMode.SELECT);
        criteria.setFetchMode("actividades", FetchMode.SELECT);
        criteria.setFetchMode("presupuestos", FetchMode.SELECT);
        //criteria.setProjection(Projections.distinct(Projections.id()));
        criteria.addOrder(Order.asc("fecha"));
		List<Proyecto> list = criteria.list();
        for(Proyecto p : list){
                all.add(p);
        }
		for( Proyecto p : all ){
			Date fecha = p.getFecha();
			if( fecha.after(configBean.getConfig().getPeriodoInicio()) && fecha.before(configBean.getConfig().getPeriodoFinal()) ){
				actual.add(p);
			}
		}

        totalMonths = new HashMap<String, Integer>();
        totalCriterio = new HashMap<String, Integer>();

        Map<Object, Number> t1 = new HashMap<Object, Number>();
        Map<Object, Number> t2 = new HashMap<Object, Number>();
        Map<Object, Number> t3 = new HashMap<Object, Number>();
        Map<Object, Number> t4 = new HashMap<Object, Number>();
        Map<Object, Number> t5 = new HashMap<Object, Number>();
        Map<Object, Number> t6 = new HashMap<Object, Number>();
        Map<Object, Number> t7 = new HashMap<Object, Number>();

        for( Proyecto p : all ){
            String criterio = MONTHS[p.getFecha().getMonth()] + " (" + (p.getFecha().getYear() + 1900) + ")";
            if( p.getAreaConocimiento().equals(C1) ){
                t1.put(criterio, t1.get(criterio) != null ? t1.get(criterio).intValue() + 1 : 1);
                getTotalCriterio().put(C1, getTotalCriterio().get(C1) != null ? getTotalCriterio().get(C1) + 1 : 1);
            } else {
                t1.put(criterio, t1.get(criterio) != null ? t1.get(criterio).intValue() : 0);
                getTotalCriterio().put(C1, getTotalCriterio().get(C1) != null ? getTotalCriterio().get(C1) : 0);
            }
            if( p.getAreaConocimiento().equals(C2) ){
                t2.put(criterio, t2.get(criterio) != null ? t2.get(criterio).intValue() + 1 : 1);
                getTotalCriterio().put(C2, getTotalCriterio().get(C2) != null ? getTotalCriterio().get(C2) + 1 : 1);
            }else {
                t2.put(criterio, t2.get(criterio) != null ? t2.get(criterio).intValue() : 0);
                getTotalCriterio().put(C2, getTotalCriterio().get(C2) != null ? getTotalCriterio().get(C2) : 0);
            }
            if( p.getAreaConocimiento().equals(C3) ){
                t3.put(criterio, t3.get(criterio) != null ? t3.get(criterio).intValue() + 1 : 1);
                getTotalCriterio().put(C3, getTotalCriterio().get(C3) != null ? getTotalCriterio().get(C3) + 1 : 1);
            } else {
                t3.put(criterio, t3.get(criterio) != null ? t3.get(criterio).intValue() : 0);
                getTotalCriterio().put(C3, getTotalCriterio().get(C3) != null ? getTotalCriterio().get(C3) : 0);
            }
            if( p.getAreaConocimiento().equals(C4) ){
                t4.put(criterio, t4.get(criterio) != null ? t4.get(criterio).intValue() + 1 : 1);
                getTotalCriterio().put(C4, getTotalCriterio().get(C4) != null ? getTotalCriterio().get(C4) + 1 : 1);
            } else {
                t4.put(criterio, t4.get(criterio) != null ? t4.get(criterio).intValue() : 0);
                getTotalCriterio().put(C4, getTotalCriterio().get(C4) != null ? getTotalCriterio().get(C4) : 0);
            }
            if( p.getAreaConocimiento().equals(C5) ){
                t5.put(criterio, t5.get(criterio) != null ? t5.get(criterio).intValue() + 1 : 1);
                getTotalCriterio().put(C5, getTotalCriterio().get(C5) != null ? getTotalCriterio().get(C5) + 1 : 1);
            } else {
                t5.put(criterio, t5.get(criterio) != null ? t5.get(criterio).intValue() : 0);
                getTotalCriterio().put(C5, getTotalCriterio().get(C5) != null ? getTotalCriterio().get(C5) : 0);
            }
            if( p.getAreaConocimiento().equals(C6) ){
                t6.put(criterio, t6.get(criterio) != null ? t6.get(criterio).intValue() + 1 : 1);
                getTotalCriterio().put(C6, getTotalCriterio().get(C6) != null ? getTotalCriterio().get(C6) + 1 : 1);
            } else {
                t6.put(criterio, t6.get(criterio) != null ? t6.get(criterio).intValue() : 0);
                getTotalCriterio().put(C6, getTotalCriterio().get(C6) != null ? getTotalCriterio().get(C6) : 0);
            }
            if( p.getAreaConocimiento().equals(C7) ){
                t7.put(criterio, t7.get(criterio) != null ? t7.get(criterio).intValue() + 1 : 1);
                getTotalCriterio().put(C7, getTotalCriterio().get(C7) != null ? getTotalCriterio().get(C7) + 1 : 1);
            } else {
                t7.put(criterio, t7.get(criterio) != null ? t7.get(criterio).intValue() : 0);
                getTotalCriterio().put(C7, getTotalCriterio().get(C7) != null ? getTotalCriterio().get(C7) : 0);
            }

            if( !totalMonths.containsKey(criterio)){
                 totalMonths.put(criterio, 1);
            } else {
                 totalMonths.put(criterio, totalMonths.get(criterio) + 1);
            }

        }

        ChartSeries a = new ChartSeries();
        a.setLabel(C1);
        a.setData(t1);
        ChartSeries b = new ChartSeries();
        b.setLabel(C2);
        b.setData(t2);
        ChartSeries c = new ChartSeries();
        c.setLabel(C3);
        c.setData(t3);
        ChartSeries d = new ChartSeries();
        d.setLabel(C4);
        d.setData(t4);
        ChartSeries e = new ChartSeries();
        e.setLabel(C5);
        e.setData(t5);
        ChartSeries f = new ChartSeries();
        f.setLabel(C6);
        f.setData(t6);
        ChartSeries g = new ChartSeries();
        g.setLabel(C7);
        g.setData(t7);

        model.addSeries(a);
        model.addSeries(b);
        model.addSeries(c);
        model.addSeries(d);
        model.addSeries(e);
        model.addSeries(f);
        model.addSeries(g);
	}

	public PieChartModel getProyectosByArea(){
		PieChartModel pieModel = new PieChartModel();
		Map<String, Number> values = new HashMap<String, Number>();
		for( Proyecto p : actual ){
			String criterio = p.getAreaConocimiento();
			values.put(criterio, values.get(criterio) != null ? values.get(criterio).intValue() + 1 : 1);
		}
		pieModel.setData(values);
		return pieModel;
	}

	public CartesianChartModel getAllProyectosByArea(){
		return model;
	}

    public int getTotalFinal(){
        int total = 0;
        for( Integer parcial : getTotalMonths().values() ){
            total += parcial;
        }
        return total;
    }

	public ConfigBean getConfigBean() {
		return configBean;
	}


	public void setConfigBean(ConfigBean configBean) {
		this.configBean = configBean;
	}

	public BitacoraBean getBitacora() {
		return bitacora;
	}

	public void setBitacora(BitacoraBean bitacora) {
		this.bitacora = bitacora;
	}

	public SessionBean getSessionBean() {
		return sessionBean;
	}

	public void setSessionBean(SessionBean sessionBean) {
		this.sessionBean = sessionBean;
	}

    public Map<String, Integer> getTotalMonths() {
        return totalMonths;
    }

    public Map<String, Integer> getTotalCriterio() {
        return totalCriterio;
    }

    public Date getDate(){
        return new Date();
    }
}
