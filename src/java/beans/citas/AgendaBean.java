/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans.citas;

import base.Dao;
import beans.Bean;
import beans.login.LoginBean;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import org.primefaces.component.schedule.Schedule;
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
        Dao dao=new Dao();
        DatosDoctor doc = (DatosDoctor) dao.getTabla(DatosDoctor.class).get(0);
        AgendaBean cit=new AgendaBean();
        List<ScheduleEvent> citas = cit.obtenerEventos(doc);
        System.out.println(citas.size());
    }

    private List<ScheduleEvent> obtenerEventos(DatosDoctor datosDoctor) {
        List<ScheduleEvent> lista=new LinkedList<ScheduleEvent>();
        Set<Cita> citas = datosDoctor.getCitas();
        for(Cita cita:citas){
            DefaultScheduleEvent evento=new DefaultScheduleEvent(cita.getDatosPaciente().getApePat()+" "+cita.getDatosPaciente().getNom()+"-"+cita.getDesCita(), cita.getFechaInicial(), cita.getFechaFinal(), false);
            lista.add(evento);
        }
        return lista;
    }

    public void agregarCita(Cita cita) {
        agenda.addEvent(new DefaultScheduleEvent(cita.getDatosPaciente().getApePat()+" "+cita.getDatosPaciente().getNom()+"-"+cita.getDesCita(), cita.getFechaInicial(), cita.getFechaFinal(), false));
    }
}
