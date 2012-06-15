package beans;

import configuradores.Configurador;

/**
 *
 * @author alberto
 */
public class ConfiguracionBean {

    private String rutaWeb = Configurador.getCfg("rutaWeb");
    private String delimitador = Configurador.getCfg("delimitador");

    public ConfiguracionBean() {
    }

    /**
     * @return the rutaWeb
     */
    public String getRutaWeb() {
        return rutaWeb;
    }

    /**
     * @param rutaWeb the rutaWeb to set
     */
    public void setRutaWeb(String rutaWeb) {
        this.rutaWeb = rutaWeb;
    }

    /**
     * @return the delimitador
     */
    public String getDelimitador() {
        return delimitador;
    }

    /**
     * @param delimitador the delimitador to set
     */
    public void setDelimitador(String delimitador) {
        this.delimitador = delimitador;
    }
}
