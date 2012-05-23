package beans.expedientes;

import beans.Bean;

/**
 *
 * @author Alberto
 */
public class VistaExpedienteBean extends Bean{

    private String ruta;

    public VistaExpedienteBean(){
        ruta="Expos";
        
    }
    
    public VistaExpedienteBean(String ruta){
        this.ruta=ruta;
    }
    
    /**
     * @return the ruta
     */
    public String getRuta() {
        return ruta;
    }

    /**
     * @param ruta the ruta to set
     */
    public void setRuta(String ruta) {
        this.ruta = ruta;
    }
    
    
}
