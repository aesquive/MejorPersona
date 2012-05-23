/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package documentadores;

import base.Dao;
import com.itextpdf.text.*;
import com.itextpdf.text.html.simpleparser.ImageStore;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import pojos.DatosPaciente;
import pojos.ExpedienteComidas;
import pojos.ExpedienteDatos;
import pojos.ExpedienteMatriz;

/**
 *
 * @author Alberto
 */
public class GeneradorExpedientes {

    private final static String ruta = "./web/";
    private DatosPaciente datosPaciente;
    private ExpedienteDatos expedienteDatos;
    private ExpedienteComidas expedienteComidas;
    private ExpedienteMatriz expedienteMatriz;
    private Document document;
    private int idPaciente;

    public GeneradorExpedientes(int idPaciente, DatosPaciente datosPaciente, ExpedienteDatos expedienteDatos, ExpedienteComidas expedienteComidas, ExpedienteMatriz expedienteMatriz) {
        this.idPaciente = idPaciente;
        this.datosPaciente = datosPaciente;
        this.expedienteDatos = expedienteDatos;
        this.expedienteComidas = expedienteComidas;
        this.expedienteMatriz = expedienteMatriz;

        document = generarDocumento();
    }

    public String generar() {
        document.open();
        escribirArchivo();
        document.close();
        return idPaciente + ".pdf";
    }


    private Document generarDocumento() {
        try {
            document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(ruta + "expedientes/" + idPaciente + ".pdf"));
            return document;
        } catch (DocumentException ex) {
            Logger.getLogger(GeneradorExpedientes.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(GeneradorExpedientes.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private void escribirArchivo() {
        agregarDocumento(generarEncabezado());
        agregarDocumento(crearTabla(generarPropiedades(datosPaciente),new float[]{.3f, 2f}));
    }

    private void agregarDocumento(Element elemento) {
        try {
            document.add(elemento);
        } catch (DocumentException ex) {
            Logger.getLogger(GeneradorExpedientes.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    private PdfPTable crearTabla(List<String[]> propiedades , float[] dimensiones) {
        PdfPTable tabla = new PdfPTable(dimensiones);
        tabla.setWidthPercentage(95);
        tabla.getDefaultCell().setBorder(0);
        tabla.setHorizontalAlignment(Element.ALIGN_LEFT);
        for (int indice=0;indice<propiedades.get(0).length;indice++) {
            tabla.addCell(new Phrase(propiedades.get(0)[indice], new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD)));
            tabla.addCell(new Phrase(propiedades.get(1)[indice], new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.UNDERLINE)));
            
        }

        return tabla;
    }

    private List<String[]> generarPropiedades(DatosPaciente datosPaciente) {
        String[] nombres=new String[]{"Nombre:"
                                    ,"Domicilio:",
                                    "Telefono:",
                                    "Celular:",
                                    "Correo:"};
        String[] valores=new String[]{datosPaciente.getApePat()+datosPaciente.getApeMat()+datosPaciente.getNom()
                                       ,datosPaciente.getDomicilio(),
                                        datosPaciente.getTel(),
                                        datosPaciente.getCel(),
                                        datosPaciente.getCorreo()};
        List<String[]> lista=new LinkedList<String[]>();
        lista.add(nombres);
        lista.add(valores);
        return lista;
    }
    
    public static void main(String[] args) {
        Dao dao=new Dao();
        GeneradorExpedientes gen=new GeneradorExpedientes(1, dao.getPaciente(1), null, null, null);
        gen.generar();
    }

    private PdfPTable generarEncabezado() {
            PdfPTable tabla = new PdfPTable(new float[]{1f});
            tabla.setWidthPercentage(95);
            tabla.getDefaultCell().setBorder(0);
            tabla.setHorizontalAlignment(Element.ALIGN_CENTER);
            Image imagen=crearImagen(ruta+"imagenes/LogoMejorPersona.png");
            tabla.addCell(imagen);
            System.out.println("acaba");
            return tabla;
    }

    private Image crearImagen(String ruta) {
        try {
            Image im= Image.getInstance(ruta);  
            im.
            return im;
        } catch (BadElementException ex) {
            Logger.getLogger(GeneradorExpedientes.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(GeneradorExpedientes.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(GeneradorExpedientes.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
}
