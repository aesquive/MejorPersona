/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans.citas;

import base.Dao;
import beans.Bean;
import beans.login.LoginBean;
import configuradores.Configurador;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import mail.EnviadorCorreos;
import org.hibernate.criterion.Restrictions;
import pojos.Cita;
import pojos.Configuracion;
import pojos.DatosDoctor;
import pojos.DatosPaciente;
import util.Funciones;

/**
 *
 * @author Alberto
 */
public class CitasBean {

    private List<DatosPaciente> pacientes;
    private Dao dao;
    private Cita cita;
    private int idSeleccionado;

    /**
     * Creates a new instance of CitasBean
     */
    public CitasBean() {
        dao = new Dao();
        pacientes = dao.getTabla(DatosPaciente.class);
        Collections.sort(pacientes);
        cita = new Cita();
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

    public String guardar() {
        System.out.println("guardando");
        System.out.println("el id =" + idSeleccionado);
        System.out.println("la fecha inicio " + cita.getFechaInicial());
        System.out.println("fecha final " + cita.getFechaFinal());
        System.out.println("descr " + cita.getDesCita());
        DatosDoctor datosDoctor = ((LoginBean) Bean.getBean("loginBean")).getDatosDoctor();
        restarHoraCitas(Integer.parseInt(Configurador.getCfg("horasDif")) , dao.getTabla(Cita.class));
        guardarCita(datosDoctor);
        AgendaBean bean = (AgendaBean) Bean.getBean("agendaBean");
        bean.agregarCita(dao.queryCriterion(Cita.class, Restrictions.eq("datosDoctor",datosDoctor)));
        enviarCorreo();
        return "citas";
    }

    /**
     * @return the cita
     */
    public Cita getCita() {
        return cita;
    }

    /**
     * @param cita the cita to set
     */
    public void setCita(Cita cita) {
        this.cita = cita;
    }

    /**
     * @return the idSeleccionado
     */
    public int getIdSeleccionado() {
        return idSeleccionado;
    }

    /**
     * @param idSeleccionado the idSeleccionado to set
     */
    public void setIdSeleccionado(int idSeleccionado) {
        this.idSeleccionado = idSeleccionado;
    }

    private void guardarCita(DatosDoctor datosDoctor) {
        cita.setDatosDoctor(datosDoctor);
        cita.setDatosPaciente(dao.getPaciente(idSeleccionado));
        dao.guardarActualizar(getCita());

    }

    private void restarHoraCitas(int horas, List<Cita> tabla) {
        for(Cita c:tabla){
            Date ini=c.getFechaInicial();
            ini.setHours(ini.getHours()-horas);
            Date fin=c.getFechaFinal();
            fin.setHours(fin.getHours()-horas);
            c.setFechaInicial(ini);
            c.setFechaFinal(fin);
            dao.guardarActualizar(c);
        }
    }

    private void enviarCorreo() {
        String[] mensaje=formarMensaje();
        List<Configuracion> tabla = dao.getTabla(Configuracion.class);
        Collections.sort(tabla);
        EnviadorCorreos enviador=new EnviadorCorreos(tabla.get(0).getValor(), tabla.get(1).getValor(), tabla.get(2).getValor());
        enviador.enviar(new String[]{cita.getDatosPaciente().getCorreo()},"CITA",mensaje);
    }

    private String[] formarMensaje() {
        String saludo="Estimado(a) "+cita.getDatosPaciente().getNom()+" "+cita.getDatosPaciente().getApePat();
        String cuerpo="Recordando su cita el dia "+Funciones.DateToString(cita.getFechaInicial())+" a las "+Funciones.sacarHorario(cita.getFechaInicial());
        String despedida="Saludos";
        return new String[]{saludo,cuerpo,despedida};
    }
}
