/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans.citas;

import base.Dao;
import beans.Bean;
import beans.login.LoginBean;
import java.util.*;
import org.primefaces.event.ScheduleEntrySelectEvent;
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
public class AgendaBean extends Bean{

    private ScheduleModel agenda;
    private DatosDoctor datosDoctor;
    private TimeZone timeZone;
    private ScheduleEvent eventoSeleccionado;
    
    /** Creates a new instance of AgendaBean */
    public AgendaBean() {
        eventoSeleccionado=new DefaultScheduleEvent();
        
        datosDoctor=((LoginBean)Bean.getBean("loginBean")).getDatosDoctor();
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
    
    public static void main(String[] args) {
        TimeZone zone=TimeZone.getTimeZone("Mexico/General");
        System.out.println(zone);
    }

    private List<ScheduleEvent> obtenerEventos(DatosDoctor datosDoctor) {
        List<ScheduleEvent> lista=new LinkedList<ScheduleEvent>();
        Set<Cita> citas = datosDoctor.getCitas();
        for(Cita original:citas){
            Cita cita = original;
            Date ini=cita.getFechaInicial();
            ini.setHours(ini.getHours()+1);
            Date fin=cita.getFechaFinal();
            fin.setHours(fin.getHours()+1);
            DefaultScheduleEvent evento=new DefaultScheduleEvent(cita.getDatosPaciente().getApePat()+" "+cita.getDatosPaciente().getNom(), ini , fin, false);
            lista.add(evento);
        }
        return lista;
    }

    public void agregarCita(List<Cita> citas) {
       List<ScheduleEvent> lista=new LinkedList<ScheduleEvent>();
        for(Cita original:citas){
            Cita cita = original;
            Date ini=cita.getFechaInicial();
            ini.setHours(ini.getHours()+1);
            Date fin=cita.getFechaFinal();
            fin.setHours(fin.getHours()+1);
            DefaultScheduleEvent evento=new DefaultScheduleEvent(cita.getDatosPaciente().getApePat()+" "+cita.getDatosPaciente().getNom(), ini , fin, false);
            lista.add(evento);
        }
        agenda=new DefaultScheduleModel(lista);
    }

    public void seleccionEvento(ScheduleEntrySelectEvent event){
        System.out.println("entro a ver el evento seleccion");
        eventoSeleccionado=new DefaultScheduleEvent("Titulo", Calendar.getInstance().getTime()  ,Calendar.getInstance().getTime());
        this.eventoSeleccionado=event.getScheduleEvent();
        System.out.println("el evento es "+eventoSeleccionado);
        System.out.println("tiene "+eventoSeleccionado.getTitle());
    }
    
    /**
     * @return the timeZone
     */
    public TimeZone getTimeZone() {
        return timeZone;
    }

    /**
     * @param timeZone the timeZone to set
     */
    public void setTimeZone(TimeZone timeZone) {
        this.timeZone = timeZone;
    }

    /**
     * @return the eventoSeleccionado
     */
    public ScheduleEvent getEventoSeleccionado() {
        return eventoSeleccionado;
    }

    /**
     * @param eventoSeleccionado the eventoSeleccionado to set
     */
    public void setEventoSeleccionado(ScheduleEvent eventoSeleccionado) {
        this.eventoSeleccionado = eventoSeleccionado;
    }
}
