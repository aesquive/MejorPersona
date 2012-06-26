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
import pojos.Cita;
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
    private LoginBean loginBean;

    /**
     * Creates a new instance of CitasBean
     */
    public CitasBean() {
        this.loginBean = (LoginBean) Bean.getBean("loginBean");
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
        DatosDoctor datosDoctor = loginBean.getDatosDoctor();
        guardarCita(datosDoctor);
        enviarCorreo();
        loginBean.setMostrarMensaje(true);
        loginBean.setMensaje("La cita se ha guardado exitosamente");
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
        int horasDif = Integer.parseInt(Configurador.getCfg("horasDif"));
        if (cita.getFechaInicial() != null && cita.getFechaFinal() != null) {

            Date fechaInicial = cita.getFechaInicial();
            Date fechaFinal = cita.getFechaFinal();
            fechaInicial.setHours(fechaInicial.getHours() + horasDif);
            fechaFinal.setHours(fechaFinal.getHours() + horasDif);
            cita.setDatosDoctor(datosDoctor);
            cita.setDatosPaciente(dao.getPaciente(idSeleccionado));

            dao.guardarActualizar(getCita());
            return;
        }
        loginBean.setMensaje("Debe definirse fecha final y fecha inicial");
    }

    private void enviarCorreo() {
        String[] mensaje = formarMensaje();
        EnviadorCorreos enviador = new EnviadorCorreos(Configurador.getCfg("enviadorCorreoSmtp"), Configurador.getCfg("enviadorCorreoRemitente"), Configurador.getCfg("enviadorCorreoPassword"), Configurador.getCfg("enviadorCorreoPuerto"));
        enviador.enviar(new String[]{cita.getDatosPaciente().getCorreo()}, "CITA", mensaje);
    }

    private String[] formarMensaje() {
        String saludo = "Estimado(a) " + cita.getDatosPaciente().getNom() + " " + cita.getDatosPaciente().getApePat();
        String cuerpo = "Recordando su cita el dia " + Funciones.DateToString(cita.getFechaInicial()) + " a las " + Funciones.sacarHorario(cita.getFechaInicial());
        String despedida = "Saludos";
        return new String[]{saludo, cuerpo, despedida};
    }
}
