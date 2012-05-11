/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans.citas;

import base.Dao;
import beans.Bean;
import beans.login.LoginBean;
import java.util.Date;
import java.util.List;
import javax.faces.event.ActionEvent;
import pojos.Cita;
import pojos.DatosDoctor;
import pojos.DatosPaciente;

/**
 *
 * @author Alberto
 */
public class CitasBean {

    
    private List<DatosPaciente> pacientes;
    private Dao dao;
    private DatosPaciente seleccionado;
    private Date fechaInicial;
    private Date fechaFinal;
    private String comentarios;
    
    /**
     * Creates a new instance of CitasBean
     */
    public CitasBean() {
        dao=new Dao();
        pacientes=dao.getTabla(DatosPaciente.class);
        
    }

    /**
     * @return the pacientes
     */
    public List<DatosPaciente> getPacientes() {
        return pacientes;
    }

    /**
     * @param pacientes the pacientes to set
     */
    public void setPacientes(List<DatosPaciente> pacientes) {
        this.pacientes = pacientes;
    }

    /**
     * @return the dao
     */
    public Dao getDao() {
        return dao;
    }

    /**
     * @param dao the dao to set
     */
    public void setDao(Dao dao) {
        this.dao = dao;
    }

    /**
     * @return the seleccionado
     */
    public DatosPaciente getSeleccionado() {
        return seleccionado;
    }

    /**
     * @param seleccionado the seleccionado to set
     */
    public void setSeleccionado(DatosPaciente seleccionado) {
        this.seleccionado = seleccionado;
    }

    /**
     * @return the fechaInicial
     */
    public Date getFechaInicial() {
        return fechaInicial;
    }

    /**
     * @param fechaInicial the fechaInicial to set
     */
    public void setFechaInicial(Date fechaInicial) {
        this.fechaInicial = fechaInicial;
    }

    /**
     * @return the fechaFinal
     */
    public Date getFechaFinal() {
        return fechaFinal;
    }

    /**
     * @param fechaFinal the fechaFinal to set
     */
    public void setFechaFinal(Date fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    /**
     * @return the comentarios
     */
    public String getComentarios() {
        return comentarios;
    }

    /**
     * @param comentarios the comentarios to set
     */
    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    public String guardar(){
        System.out.println("guardando");
        DatosDoctor datosDoctor = ((LoginBean)Bean.getBean("loginBean")).getDatosDoctor();
        Cita cita=new Cita(datosDoctor, seleccionado, comentarios, fechaInicial, fechaFinal);
        dao.guardarActualizar(cita);
        AgendaBean bean = (AgendaBean) Bean.getBean("agendaBean");
        bean.agregarCita(cita);
        System.out.println("acabe de guardar cita");
        return "citas";
    }
}
