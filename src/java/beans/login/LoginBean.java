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
public class LoginBean extends Bean{

    private String usuario;
    private String password;
    private boolean contenidoDesbloqueado;
    private DatosDoctor datosDoctor;
    private DatosPaciente datosPaciente;
    /** Creates a new instance of LoginBean */
    public LoginBean() {
        
    }
    
    public String verificarUsuario(){
        datosDoctor=null;
        datosPaciente=null;
        Dao dao=new Dao();
        List<DatosDoctor> doctores=dao.getTabla(DatosDoctor.class);
        for(DatosDoctor doc:doctores){
            if(doc.getUsu().equals(usuario) && doc.getPas().equals(password)){
                datosDoctor=doc;
                this.contenidoDesbloqueado=true;
                return "documentos";
            }
        }
        
        List<DatosPaciente> pacientes=dao.getTabla(DatosPaciente.class);
        for(DatosPaciente pac:pacientes){
            if(pac.getUsuario()!=null && pac.getUsuario().equalsIgnoreCase(usuario) && pac.getUsuario().equalsIgnoreCase(password)){
                datosPaciente=pac;
                this.contenidoDesbloqueado=false;
                return "documentos";
            }
        }
                
        
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
}
