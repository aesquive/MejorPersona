package beans.login;

import base.Dao;
import beans.Bean;
import java.util.List;
import pojos.DatosDoctor;

/**
 *
 * @author Alberto
 */
public class LoginBean extends Bean{

    private String usuario;
    private String password;
    private DatosDoctor datosDoctor;
    /** Creates a new instance of LoginBean */
    public LoginBean() {
        
    }
    
    public String verificarUsuario(){
        Dao dao=new Dao();
        List<DatosDoctor> doctores=dao.getTabla(DatosDoctor.class);
        for(DatosDoctor doc:doctores){
            if(doc.getUsu().equals(usuario) && doc.getPas().equals(password)){
                datosDoctor=doc;
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
}
