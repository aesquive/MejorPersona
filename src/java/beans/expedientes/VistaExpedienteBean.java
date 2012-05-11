package beans.expedientes;

import beans.Bean;

/**
 *
 * @author Alberto
 */
public class VistaExpedienteBean extends Bean{

    private ExpedienteBean expedienteBean;
    
    public VistaExpedienteBean() {
        expedienteBean=new ExpedienteBean();
    }
    
    public VistaExpedienteBean(ExpedienteBean expedienteBean)
    {
        this.expedienteBean=expedienteBean;
    }

    /**
     * @return the expedienteBean
     */
    public ExpedienteBean getExpedienteBean() {
        return expedienteBean;
    }

    /**
     * @param expedienteBean the expedienteBean to set
     */
    public void setExpedienteBean(ExpedienteBean expedienteBean) {
        this.expedienteBean = expedienteBean;
    }
}
