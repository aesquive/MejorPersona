/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package documentadores.recetas;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import configuradores.Configurador;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alberto
 */
public class GeneradorRecetas {

    private final String rutaLocal = Configurador.getCfg("rutaLocal");
    private final String delimitador = Configurador.getCfg("delimitador");
    private String nombreArchivoNuevo;
    private String nombreArchivoBase;
    private PdfStamper stamper;

    public GeneradorRecetas(String nombreArchivoNuevo, String nombreArchivoBase) {
        this.nombreArchivoNuevo = nombreArchivoNuevo;
        this.nombreArchivoBase = nombreArchivoBase;
        obtenerStamper();
    }

    private void obtenerStamper() {
        try {
            PdfReader reader = new PdfReader(rutaLocal + "baseRecetas" + delimitador + nombreArchivoBase);
            this.stamper = new PdfStamper(reader, new FileOutputStream(rutaLocal + "recetas" + delimitador + nombreArchivoNuevo));
        } catch (DocumentException ex) {
            Logger.getLogger(GeneradorRecetas.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(GeneradorRecetas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String generarReceta(List<String> texto, int posX, int posY) {
        try {
            
            int posActualY = posY;
            for (String s : texto) {
                Paragraph parrafo = generarParrafo(s);
                ColumnText.showTextAligned(stamper.getOverContent(1), Element.ALIGN_LEFT, parrafo, posX, posActualY, 0);
                posActualY-=12;
            }
            stamper.close();
            return Configurador.getCfg("rutaWeb") + "recetas"+delimitador + nombreArchivoNuevo;
        } catch (DocumentException ex) {
            Logger.getLogger(GeneradorRecetas.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(GeneradorRecetas.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static void main(String[] args) {
        GeneradorRecetas gen = new GeneradorRecetas("1.pdf", "recetasChuy1.pdf");
        List<String> lista=new LinkedList<String>();
        lista.add("holaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        lista.add("adiossssssssssssssssssssss");
        String contador="";
        for(int t=0;t<90;t++){
            String car=t>=10 ? String.valueOf(t%10) : String.valueOf(t);
            contador+=String.valueOf(car);
        }
        lista.add(contador);
        gen.generarReceta(lista, 40, 640);
    }

    private Paragraph generarParrafo(String texto) {
        Paragraph p = new Paragraph(texto, new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL));

        return p;
    }
}
