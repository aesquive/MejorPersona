/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package documentadores;

import base.Dao;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.border.Border;
import pojos.DatosPaciente;
import pojos.ExpedienteComidas;
import pojos.ExpedienteDatos;
import pojos.ExpedienteMatriz;
import util.Funciones;

/**
 *
 * @author Alberto
 */
public class GeneradorExpedientes {

    private final static String ruta = "/var/www/private/";
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
        agregarDocumento(generarImagenEncabezado());
        //agregarDocumento(generarEncabezado());
        agregarDocumento(new Phrase(" "));
        agregarDocumento(crearTabla(generarPropiedades(datosPaciente), new float[]{.3f, 2f}));
        agregarDocumento(new Phrase(" "));
        agregarDocumento(crearTabla(generarPropiedades(expedienteDatos), new float[]{2f, 2f}));
        agregarDocumento(new Phrase(" "));
        agregarDocumento(crearTabla(generarPropiedadesMedidas(expedienteDatos), new float[]{1f, 1f, 1f, 1f}));

        agregarDocumento(Chunk.NEWLINE);
        agregarComida("Desayuno :", expedienteComidas.getDesDesayuno(), expedienteComidas.getHoraDesayuno(), expedienteComidas.getLugDesayuno(), expedienteComidas.getComeSoloDesayuno());
        agregarDocumento(Chunk.NEWLINE);

        agregarComida("Comida :", expedienteComidas.getDesComida(), expedienteComidas.getHoraComida(), expedienteComidas.getLugComida(), expedienteComidas.getComeSoloComida());
        agregarDocumento(Chunk.NEWLINE);

        agregarComida("Cena :", expedienteComidas.getDesCena(), expedienteComidas.getHoraCena(), expedienteComidas.getLugCena(), expedienteComidas.getComeSoloCena());

        if (document.getPageNumber() == 0) {
            document.newPage();
        }
        agregarDocumento(crearMatrizPadecimientos());
        agregarDocumento(Chunk.NEWLINE);
        agregarDocumento(crearMatrizPadecimientosPersonales());
    }

    private void agregarComida(String desayuno, String desDesayuno, Date horaDesayuno, String lugDesayuno, String comeSoloDesayuno) {

        agregarDocumento(new Phrase(desayuno, new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD)));
        agregarDocumento(Chunk.NEWLINE);
        agregarDocumento(new Phrase(desDesayuno, new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.UNDERLINE)));
        agregarDocumento(Chunk.NEWLINE);
        String[] nombres = new String[]{"Hora :", "Lugar :", "Come solo : ", " "};
        String[] valores = new String[]{Funciones.sacarHorario(horaDesayuno), lugDesayuno, comeSoloDesayuno, " "};
        List<String[]> lis = new ArrayList<String[]>();
        lis.add(nombres);
        lis.add(valores);
        agregarDocumento(crearTabla(lis, new float[]{1f, 1f, 1f, 1f}));
    }

    private void agregarDocumento(Element elemento) {
        try {
            document.add(elemento);
        } catch (DocumentException ex) {
            Logger.getLogger(GeneradorExpedientes.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private PdfPTable crearTabla(List<String[]> propiedades, float[] dimensiones) {
        PdfPTable tabla = new PdfPTable(dimensiones);
        tabla.setWidthPercentage(95);
        tabla.getDefaultCell().setBorder(0);
        tabla.setHorizontalAlignment(Element.ALIGN_LEFT);
        for (int indice = 0; indice < propiedades.get(0).length; indice++) {
            tabla.addCell(new Phrase(propiedades.get(0)[indice], new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD)));
            tabla.addCell(new Phrase(propiedades.get(1)[indice], new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.UNDERLINE)));

        }

        return tabla;
    }

    private List<String[]> generarPropiedadesMedidas(ExpedienteDatos expedienteDatos) {
        String[] nombres = new String[]{"Peso Inicial :", "Talla :",
            "Estructura Osea :",
            "Peso Ideal :", "IMC : ", " ", "Cadera :", "Cintura :", "Busto :", " "
        };

        String[] valores = new String[]{expedienteDatos.getPesoInicial().toString() + " kg", expedienteDatos.getTalla().toString() + " m", expedienteDatos.getEstructuraOsea(), expedienteDatos.getPesoIdeal().toString() + " kg",
            Funciones.truncar(2, ((Double) (expedienteDatos.getPesoInicial() / (expedienteDatos.getTalla() * expedienteDatos.getTalla())))), " ",
            expedienteDatos.getCadera() + " cm", expedienteDatos.getCintura() + " cm", expedienteDatos.getBusto() + " cm", " "
        };
        List<String[]> lista = new LinkedList<String[]>();
        lista.add(nombres);
        lista.add(valores);
        return lista;
    }

    private List<String[]> generarPropiedades(ExpedienteDatos expedienteDatos) {
        String[] nombres = new String[]{"Edad de comienzo de la obesidad :", "Probable evento disparador de la obesidad:",
            "Tratamientos previos:",
            "Toma algun medicamento actualmente :",};

        String[] valores = new String[]{expedienteDatos.getEdaComObe().toString(), expedienteDatos.getEvtDisObe(),
            expedienteDatos.getTratPrev(),
            expedienteDatos.getMedAct()
        };
        List<String[]> lista = new LinkedList<String[]>();
        lista.add(nombres);
        lista.add(valores);
        return lista;
    }

    private List<String[]> generarPropiedades(DatosPaciente datosPaciente) {
        String[] nombres = new String[]{"Nombre:", "Domicilio:",
            "Telefono:",
            "Celular:",
            "Correo:"};
        String[] valores = new String[]{datosPaciente.getApePat() +" "+ datosPaciente.getApeMat() +" "+ datosPaciente.getNom(), datosPaciente.getDomicilio(),
            datosPaciente.getTel(),
            datosPaciente.getCel(),
            datosPaciente.getCorreo()};
        List<String[]> lista = new LinkedList<String[]>();
        lista.add(nombres);
        lista.add(valores);
        return lista;
    }

    public static void main(String[] args) {
        Dao dao = new Dao();
        DatosPaciente paciente = dao.getPaciente(1);
        GeneradorExpedientes gen = new GeneradorExpedientes(1, paciente, (ExpedienteDatos) paciente.getExpedienteDatoses().iterator().next(), (ExpedienteComidas) paciente.getExpedienteComidases().iterator().next(), (ExpedienteMatriz)paciente.getExpedienteMatrizs().iterator().next());
        gen.generar();
    }

    private PdfPTable generarImagenEncabezado() {
        PdfPTable tabla = new PdfPTable(new float[]{10f, 4f});
        tabla.setWidthPercentage(50);
        tabla.getDefaultCell().setBorder(0);
        tabla.setHorizontalAlignment(Element.ALIGN_CENTER);
        Image imagen = crearImagen(ruta + "imagenes/LogoMejorPersona.png");
        tabla.addCell(new Phrase("EXPEDIENTE CLINICO", new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD)));
        tabla.addCell(imagen);
        return tabla;
    }

    private Image crearImagen(String ruta) {
        try {
            Image im = Image.getInstance(ruta);
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

    private PdfPTable crearMatrizPadecimientos() {
        PdfPTable tabla = new PdfPTable(new float[]{.9f,.9f,.9f,.9f});
        tabla.setWidthPercentage(95);
        
        tabla.setHorizontalAlignment(Element.ALIGN_LEFT);
        String[] izq = new String[]{" ", "Obesidad", "Diabetes", "Hipertension", "Trigliceridos", "Colesterol", "Alergias", "Tabaquismo", "Alcoholismo", "Transtornos mentales", "Problemas cardiacos"};
        String[] mam = new String[]{"Mama", expedienteMatriz.getObeMama(), expedienteMatriz.getDiaMama(),expedienteMatriz.getHipMama(), expedienteMatriz.getTriMama(), expedienteMatriz.getColMama(), expedienteMatriz.getAleMama(), expedienteMatriz.getTabMama(), expedienteMatriz.getAlcMama(), expedienteMatriz.getMensMama(), expedienteMatriz.getCardMama()};
        String[] pap = new String[]{"Papa", expedienteMatriz.getObePapa(), expedienteMatriz.getDiaPapa(),expedienteMatriz.getHipPapa(),expedienteMatriz.getTriPapa(), expedienteMatriz.getColPapa(), expedienteMatriz.getAlePapa(), expedienteMatriz.getTabPapa(), expedienteMatriz.getAlcPapa(), expedienteMatriz.getMensPapa(), expedienteMatriz.getCardPapa()};
        String[] paciente = new String[]{"Paciente", expedienteMatriz.getObePac(), expedienteMatriz.getDiaPac(),expedienteMatriz.getHipPac(), expedienteMatriz.getTriPac(), expedienteMatriz.getColPac(), expedienteMatriz.getAlePac(), expedienteMatriz.getTabPac(), expedienteMatriz.getAlcPac(), expedienteMatriz.getMensPac(), expedienteMatriz.getCardPac()};

        for (int t = 0; t < izq.length; t++) {
            tabla.addCell(new Phrase(izq[t], new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD)));
            if(t==0){
                tabla.addCell(new Phrase(mam[t], new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD)));
                tabla.addCell(new Phrase(pap[t], new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD)));
                tabla.addCell(new Phrase(paciente[t], new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD)));
            }else{
            tabla.addCell(new Phrase(mam[t], new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL)));
            tabla.addCell(new Phrase(pap[t], new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL)));
            tabla.addCell(new Phrase(paciente[t], new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL)));
                
            }
        }
        return tabla;
    }

    private Element crearMatrizPadecimientosPersonales() {
        PdfPTable tabla = new PdfPTable(new float[]{1.5f,.9f});
        tabla.setWidthPercentage(50);
        
        tabla.setHorizontalAlignment(Element.ALIGN_LEFT);
        String[] izq = new String[]{"Estrenimiento","Retencion de liquidos        Edema de miembros menores         Inferiores","Varices"};
        String[] der=new String[]{expedienteMatriz.getEstrePac(),expedienteMatriz.getRetLiqPac(),expedienteMatriz.getVarPac()};
        for (int t = 0; t < izq.length; t++) {
            tabla.addCell(new Phrase(izq[t], new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD)));
            
            tabla.addCell(new Phrase(der[t], new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL)));
                
        }
        return tabla;
    }
}
