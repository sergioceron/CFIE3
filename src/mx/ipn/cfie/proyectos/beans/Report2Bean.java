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
public class Report2Bean {
	static final String MONTHS[] = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};
    static final String C1 = "Proyecto Investigacion";
    static final String C2 = "Proyecto Multidisciplinario";
    static final String C3 = "Propuesta de Estudio";
    
	private ConfigBean configBean;
	private BitacoraBean bitacora;
	private SessionBean sessionBean;
    private Map<String, Integer> totalMonths;
    private Map<String, Integer> totalCriterio;

	private List<Proyecto> actual = new ArrayList<Proyecto>();
	List<Proyecto> all = new ArrayList<Proyecto>();
    CartesianChartModel model = new CartesianChartModel();

	public Report2Bean(){

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

        for( Proyecto p : all ){
            String criterio = MONTHS[p.getFecha().getMonth()] + " (" + (p.getFecha().getYear() + 1900) + ")";
            if( p.getTipoProyecto().equals(C1) ){
                t1.put(criterio, t1.get(criterio) != null ? t1.get(criterio).intValue() + 1 : 1);
                getTotalCriterio().put(C1, getTotalCriterio().get(C1) != null ? getTotalCriterio().get(C1) + 1 : 1);
            } else {
                t1.put(criterio, t1.get(criterio) != null ? t1.get(criterio).intValue() : 0);
                getTotalCriterio().put(C1, getTotalCriterio().get(C1) != null ? getTotalCriterio().get(C1) : 0);
            }
            if( p.getTipoProyecto().equals(C2) ){
                t2.put(criterio, t2.get(criterio) != null ? t2.get(criterio).intValue() + 1 : 1);
                getTotalCriterio().put(C2, getTotalCriterio().get(C2) != null ? getTotalCriterio().get(C2) + 1 : 1);
            }else {
                t2.put(criterio, t2.get(criterio) != null ? t2.get(criterio).intValue() : 0);
                getTotalCriterio().put(C2, getTotalCriterio().get(C2) != null ? getTotalCriterio().get(C2) : 0);
            }
            if( p.getTipoProyecto().equals(C3) ){
                t3.put(criterio, t3.get(criterio) != null ? t3.get(criterio).intValue() + 1 : 1);
                getTotalCriterio().put(C3, getTotalCriterio().get(C3) != null ? getTotalCriterio().get(C3) + 1 : 1);
            } else {
                t3.put(criterio, t3.get(criterio) != null ? t3.get(criterio).intValue() : 0);
                getTotalCriterio().put(C3, getTotalCriterio().get(C3) != null ? getTotalCriterio().get(C3) : 0);
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

        model.addSeries(a);
        model.addSeries(b);
        model.addSeries(c);
	}

	public PieChartModel getProyectosByTipo(){
		PieChartModel pieModel = new PieChartModel();
		Map<String, Number> values = new HashMap<String, Number>();
		for( Proyecto p : actual ){
			String criterio = p.getTipoProyecto();
			values.put(criterio, values.get(criterio) != null ? values.get(criterio).intValue() + 1 : 1);
		}
		pieModel.setData(values);
		return pieModel;
	}

	public CartesianChartModel getAllProyectosByTipo(){
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
