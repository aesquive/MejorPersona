/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans.citas;

import base.Dao;
import beans.Bean;
import beans.login.LoginBean;
import java.util.*;
import org.primefaces.event.ScheduleEntryMoveEvent;
import org.primefaces.event.ScheduleEntrySelectEvent;
import org.primefaces.model.*;
import pojos.Cita;
import pojos.DatosDoctor;

/**
 *
 * @author Alberto
 */
public class AgendaBean extends Bean{

    private ScheduleModel agenda;
    private DatosDoctor datosDoctor;
    
    /** Creates a new instance of AgendaBean */
    public AgendaBean() {
        
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

    
   public void eventMove(ScheduleEntryMoveEvent event){
       System.out.println("movi el evento");
   }
    
   
}
