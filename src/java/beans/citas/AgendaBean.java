/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans.citas;

import base.Dao;
import beans.Bean;
import beans.login.LoginBean;
import java.util.*;
import org.hibernate.criterion.Restrictions;
import org.primefaces.event.DateSelectEvent;
import org.primefaces.event.ScheduleEntryMoveEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;
import pojos.Cita;
import pojos.DatosDoctor;

/**
 *
 * @author Alberto
 */
public class AgendaBean extends Bean {

    private ScheduleModel agenda;
    private DatosDoctor datosDoctor;
    private Date fechaSeleccionada;
    private List<Cita> citasFiltradas;
    private String idCitaEliminar;
    private Dao dao;
    private LoginBean loginBean;

    /**
     * Creates a new instance of AgendaBean
     */
    public AgendaBean() {
        this.loginBean = (LoginBean) Bean.getBean("loginBean");
        dao = new Dao();
        datosDoctor = loginBean.getDatosDoctor();
        List<ScheduleEvent> eventos = obtenerEventos(datosDoctor);
        agenda = new DefaultScheduleModel(eventos);
    }

    /**
     * @return the agenda
     */
    public ScheduleModel getAgenda() {
        return agenda;
    }

    /**
     * @param agenda the agenda to set
     */
    public void setAgenda(ScheduleModel agenda) {
        this.agenda = agenda;
    }

    private List<ScheduleEvent> obtenerEventos(DatosDoctor datosDoctor) {
        List<ScheduleEvent> lista = new LinkedList<ScheduleEvent>();
        Collection<Cita> citas = obtenerCitas();
        for (Cita original : citas) {
            Cita cita = original;
            Date ini = cita.getFechaInicial();
            Date fin = cita.getFechaFinal();
            DefaultScheduleEvent evento = new DefaultScheduleEvent(cita.getDatosPaciente().getApePat() + " " + cita.getDatosPaciente().getNom(), ini, fin, false);
            lista.add(evento);
        }
        return lista;
    }

    public void eventMove(ScheduleEntryMoveEvent event) {
        System.out.println("movi el evento");
    }

    /**
     * @return the fechaSeleccionada
     */
    public Date getFechaSeleccionada() {
        return fechaSeleccionada;
    }

    /**
     * @param fechaSeleccionada the fechaSeleccionada to set
     */
    public void setFechaSeleccionada(Date fechaSeleccionada) {
        this.fechaSeleccionada = fechaSeleccionada;
    }

    public void fechaEliminarSeleccionada(DateSelectEvent event) {
        System.out.println("la fecha seleccionada es " + fechaSeleccionada);
        citasFiltradas = new LinkedList<Cita>();
        Collection<Cita> obtenerCitas = obtenerCitas();
        for (Cita c : obtenerCitas) {
            Date fechaInicial = c.getFechaInicial();
            if (fechaInicial.getDay() == fechaSeleccionada.getDay() && fechaInicial.getMonth() == fechaSeleccionada.getMonth() && fechaInicial.getYear() == fechaSeleccionada.getYear()) {
                citasFiltradas.add(c);
            }
        }
    }

    /**
     * @return the citasFiltradas
     */
    public List<Cita> getCitasFiltradas() {
        return citasFiltradas;
    }

    /**
     * @param citasFiltradas the citasFiltradas to set
     */
    public void setCitasFiltradas(List<Cita> citasFiltradas) {
        this.citasFiltradas = citasFiltradas;
    }

    private Collection<Cita> obtenerCitas() {
        dao.refrescarPaciente(datosDoctor);
        //return datosDoctor.getCitas();
        return dao.getTabla(Cita.class);
    }

    public void comboCitaEliminadaCambio() {
        System.out.println("la nueva cita es " + idCitaEliminar);
    }

    public String eliminarCita() {
        if (idCitaEliminar!=null &&  !idCitaEliminar.equals("")  && !getIdCitaEliminar().equals("0") && !getIdCitaEliminar().equals("-1")) {
            System.out.println("se elimino la cita");
            dao.eliminar(dao.queryCriterion(Cita.class, Restrictions.eq("id", Integer.parseInt(idCitaEliminar))).get(0));
            loginBean.setMostrarMensaje(true);
            loginBean.setMensaje("La cita se ha eliminado correctamente");
            return "citas";
        }
        loginBean.setMostrarMensaje(true);
        loginBean.setMensaje("No ha sido posible eliminar la cita");
        return "citas";
    }

    /**
     * @return the idCitaEliminar
     */
    public String getIdCitaEliminar() {
        return idCitaEliminar;
    }

    /**
     * @param idCitaEliminar the idCitaEliminar to set
     */
    public void setIdCitaEliminar(String idCitaEliminar) {
        this.idCitaEliminar = idCitaEliminar;
    }
}
