package mx.ipn.cfie.proyectos.beans;

import mx.ipn.cfie.proyectos.dao.HibernateDao;
import mx.ipn.cfie.proyectos.model.Proyecto;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
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
public class Report1Bean {
	static final String MONTHS[] = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};
	private ConfigBean configBean;
	private BitacoraBean bitacora;
	private SessionBean sessionBean;
    private Map<String, Integer> totalMonths;
    private Map<String, Integer> totalCriterio;

	private List<Proyecto> actual = new ArrayList<Proyecto>();
	List<Proyecto> all = new ArrayList<Proyecto>();
    CartesianChartModel model = new CartesianChartModel();

	public Report1Bean(){

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
            if( p.isEvaluado() ){
                all.add(p);
            }
        }
		for( Proyecto p : all ){
			Date fecha = p.getFecha();
			if( fecha.after(configBean.getConfig().getPeriodoInicio()) && fecha.before(configBean.getConfig().getPeriodoFinal()) && p.isEvaluado() ){
				actual.add(p);
			}
		}

        totalMonths = new HashMap<String, Integer>();
        totalCriterio = new HashMap<String, Integer>();

        Map<Object, Number> elegibles = new HashMap<Object, Number>();
        Map<Object, Number> condicionados = new HashMap<Object, Number>();
        Map<Object, Number> noelegibles = new HashMap<Object, Number>();

        for( Proyecto p : all ){
            String criterio = MONTHS[p.getFecha().getMonth()] + " (" + (p.getFecha().getYear() + 1900) + ")";
            if( p.getEvaluacion().getDictamen().equals("Elegible") ){
                elegibles.put(criterio, elegibles.get(criterio) != null ? elegibles.get(criterio).intValue() + 1 : 1);
                getTotalCriterio().put("Elegibles", getTotalCriterio().get("Elegibles") != null ? getTotalCriterio().get("Elegibles") + 1 : 1);
            } else {
                elegibles.put(criterio, elegibles.get(criterio) != null ? elegibles.get(criterio).intValue() : 0);
                getTotalCriterio().put("Elegibles", getTotalCriterio().get("Elegibles") != null ? getTotalCriterio().get("Elegibles") : 0);
            }
            if( p.getEvaluacion().getDictamen().equals("Condicionado") ){
                condicionados.put(criterio, condicionados.get(criterio) != null ? condicionados.get(criterio).intValue() + 1 : 1);
                getTotalCriterio().put("Condicionados", getTotalCriterio().get("Condicionados") != null ? getTotalCriterio().get("Condicionados") + 1 : 1);
            }else {
                condicionados.put(criterio, condicionados.get(criterio) != null ? condicionados.get(criterio).intValue() : 0);
                getTotalCriterio().put("Condicionados", getTotalCriterio().get("Condicionados") != null ? getTotalCriterio().get("Condicionados") : 0);
            }
            if( p.getEvaluacion().getDictamen().equals("No Elegible") ){
                noelegibles.put(criterio, noelegibles.get(criterio) != null ? noelegibles.get(criterio).intValue() + 1 : 1);
                getTotalCriterio().put("No Elegibles", getTotalCriterio().get("No Elegibles") != null ? getTotalCriterio().get("No Elegibles") + 1 : 1);
            } else {
                noelegibles.put(criterio, noelegibles.get(criterio) != null ? noelegibles.get(criterio).intValue() : 0);
                getTotalCriterio().put("No Elegibles", getTotalCriterio().get("No Elegibles") != null ? getTotalCriterio().get("No Elegibles") : 0);
            }

            if( !totalMonths.containsKey(criterio)){
                totalMonths.put(criterio, 1);
            } else {
                totalMonths.put(criterio, totalMonths.get(criterio) + 1);
            }
        }

        ChartSeries a = new ChartSeries();
        a.setLabel("Elegibles");
        a.setData(elegibles);
        ChartSeries b = new ChartSeries();
        b.setLabel("Condicionados");
        b.setData(condicionados);
        ChartSeries c = new ChartSeries();
        c.setLabel("No Elegibles");
        c.setData(noelegibles);

        model.addSeries(a);
        model.addSeries(b);
        model.addSeries(c);
	}

	public PieChartModel getProyectosByDictamen(){
		PieChartModel pieModel = new PieChartModel();
		Map<String, Number> values = new HashMap<String, Number>();
		for( Proyecto p : actual ){
			String criterio = p.getEvaluacion().getDictamen();
			values.put(criterio, values.get(criterio) != null ? values.get(criterio).intValue() + 1 : 1);
		}
		pieModel.setData(values);
		return pieModel;
	}

	public CartesianChartModel getAllProyectosByDictamen(){
		return model;
	}

    public List<Proyecto> getElegibles(){
        List<Proyecto> elegibles = new ArrayList<Proyecto>();
        for( Proyecto p : all ){
            if( !p.getEvaluacion().getDictamen().equals("No Elegible") ){
                elegibles.add(p);
            }
        }
        return elegibles;
    }

    public int getTotalFinal(){
        int total = 0;
        for( Integer parcial : getTotalMonths().values() ){
            total += parcial;
        }
        return total;
    }

    public double getPresupuestoTotal(){
        double total = 0.0;
        for( Proyecto p : all ){
            if( !p.getEvaluacion().getDictamen().equals("No Elegible") ){
                if( p.getCantidad() != null )
                    total += p.getTotalPresupuesto();
            }
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
