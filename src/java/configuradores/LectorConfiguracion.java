package configuradores;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alberto
 */
public class LectorConfiguracion {

    private final String rutaArchivo = "/var/www/private/config/config.file";
    private HashMap<String, String> mapeo;

    public LectorConfiguracion() {
        mapeo = new HashMap<String, String>();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(rutaArchivo));
            String linea = reader.readLine();
            while (linea != null) {
                String[] partido = linea.split("=");
                mapeo.put(partido[0], partido[1]);
                linea = reader.readLine();
            }
            reader.close();

            System.out.println("configurador inicilizado");
        } catch (IOException ex) {
            Logger.getLogger(LectorConfiguracion.class.getName()).log(Level.SEVERE, null, ex);
        }

    }


    public String getCfg(String llave){
        return mapeo.get(llave);
    }
    
    public static void main(String[] args) {
        LectorConfiguracion lec = new LectorConfiguracion();
        String cfg = lec.getCfg("enviadorCorreoRemitente");
        System.out.print(cfg);
    }
}
