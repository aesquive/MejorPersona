package configuradores;

/**
 *
 * @author alberto
 */
public class Configurador {

    private static LectorConfiguracion lectorConfiguracion=new LectorConfiguracion();
    
    
    
    public static String getCfg(String llave){
        return lectorConfiguracion.getCfg(llave); 
    }
    
    public static void main(String[] args) {
        String valor = Configurador.getCfg("liga");
        String cfg = Configurador.getCfg(" ");
        System.out.println(valor);
    }
    
}
