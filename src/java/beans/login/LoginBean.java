package beans.login;

import base.Dao;
import beans.Bean;
import java.util.List;
import pojos.DatosDoctor;
import pojos.DatosPaciente;

/**
 *
 * @author Alberto
 */
public class LoginBean extends Bean {

    private String mensaje;
    private boolean mostrarMensaje;
    private String usuario;
    private String password;
    private boolean contenidoDesbloqueado;
    private DatosDoctor datosDoctor;
    private DatosPaciente datosPaciente;
    private String theme;
    
    /**
     * Creates a new instance of LoginBean
     */
    public LoginBean() {
        //theme="hot-sneaks";
        theme="cupertino";
    }

    public String verificarUsuario() {
        
        datosDoctor = null;
        datosPaciente = null;
        Dao dao = new Dao();
        List<DatosDoctor> doctores = dao.getTabla(DatosDoctor.class);
        for (DatosDoctor doc : doctores) {
            if (doc.getUsu().equals(usuario) && doc.getPas().equals(password)) {
                datosDoctor = doc;
                this.theme=doc.getTema();
                this.contenidoDesbloqueado = true;
                mensaje="";
                mostrarMensaje=false;
                return "documentos";
            }
        }

        List<DatosPaciente> pacientes = dao.getTabla(DatosPaciente.class);
        for (DatosPaciente pac : pacientes) {
            if (pac.getUsuario() != null && pac.getUsuario().equalsIgnoreCase(usuario) && pac.getPassword().equalsIgnoreCase(password)) {
                datosPaciente = pac;
                this.contenidoDesbloqueado = false;
                this.theme=pac.getTema();
                mensaje = "";
                mostrarMensaje = false;
                return "documentos";
            }
        }
        mostrarMensaje = true;
        mensaje = "Usuario y/o Password Incorrecto";
        return "index";
    }

    /**
     * @return the datosDoctor
     */
    public DatosDoctor getDatosDoctor() {
        return datosDoctor;
    }

    /**
     * @param datosDoctor the datosDoctor to set
     */
    public void setDatosDoctor(DatosDoctor datosDoctor) {
        this.datosDoctor = datosDoctor;
    }

    /**
     * @return the usuario
     */
    public String getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the contenidoDesbloqueado
     */
    public boolean isContenidoDesbloqueado() {
        return contenidoDesbloqueado;
    }

    /**
     * @param contenidoDesbloqueado the contenidoDesbloqueado to set
     */
    public void setContenidoDesbloqueado(boolean contenidoDesbloqueado) {
        this.contenidoDesbloqueado = contenidoDesbloqueado;
    }

    /**
     * @return the datosPaciente
     */
    public DatosPaciente getDatosPaciente() {
        return datosPaciente;
    }

    /**
     * @param datosPaciente the datosPaciente to set
     */
    public void setDatosPaciente(DatosPaciente datosPaciente) {
        this.datosPaciente = datosPaciente;
    }

    /**
     * @return the mensaje
     */
    public String getMensaje() {
        return mensaje;
    }

    /**
     * @param mensaje the mensaje to set
     */
    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    /**
     * @return the mostrarMensaje
     */
    public boolean isMostrarMensaje() {
        return mostrarMensaje;
    }

    /**
     * @param mostrarMensaje the mostrarMensaje to set
     */
    public void setMostrarMensaje(boolean mostrarMensaje) {
        this.mostrarMensaje = mostrarMensaje;
    }

    /**
     * @return the theme
     */
    public String getTheme() {
        return theme;
    }

    /**
     * @param theme the theme to set
     */
    public void setTheme(String theme) {
        this.theme = theme;
    }
}
