
package beans;

import java.util.Map;
import javax.faces.context.FacesContext;

public class Bean{
    
    public Bean(){
        
    }
    
    public static Bean getBean(String nombre){
        Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        return (Bean) sessionMap.get(nombre);
    }
    
    public static void ponerBean(String nombre , Bean bean){
        Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        sessionMap.put(nombre, bean);
    }
    
}