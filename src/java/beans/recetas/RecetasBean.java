/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans.recetas;

import base.Dao;
import beans.Bean;
import beans.login.LoginBean;
import configuradores.Configurador;
import documentadores.recetas.GeneradorRecetas;
import java.io.File;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import pojos.DatosDoctor;
import pojos.DatosPaciente;
import pojos.Receta;
import pojos.TipoReceta;

/**
 *
 * @author alberto
 */
public class RecetasBean {

    private String idPacienteSeleccionado;
    private String idTipoRecetaSeleccionado;
    private String contenido;
    private Receta receta;
    private boolean recetaCompleta;
    private List<DatosPaciente> pacientes;
    private List<TipoReceta> tiposRecetas;
    private LoginBean loginBean;
    private Dao dao;
    private String rutaPdf;

    public RecetasBean() {
        rutaPdf = Configurador.getCfg("rutaWeb") + "logo.pdf";
        receta = new Receta();
        this.loginBean = (LoginBean) Bean.getBean("loginBean");
        dao = new Dao();
        generarPacientes();
        generarTiposReceta();
    }

    private void generarPacientes() {
        setPacientes((List<DatosPaciente>) dao.getTabla(DatosPaciente.class));
    }

    private void generarTiposReceta() {
        DatosDoctor datosDoctor = loginBean.getDatosDoctor();
        Set<TipoReceta> tipoRecetas = datosDoctor.getTipoRecetas();
        setTiposRecetas(new LinkedList<TipoReceta>());
        for (TipoReceta rec : tipoRecetas) {
            getTiposRecetas().add(rec);
        }
    }

    /**
     * @return the receta
     */
    public Receta getReceta() {
        return receta;
    }

    /**
     * @param receta the receta to set
     */
    public void setReceta(Receta receta) {
        this.receta = receta;
    }

    /**
     * @return the pacientes
     */
    public List<DatosPaciente> getPacientes() {
        return pacientes;
    }

    /**
     * @param pacientes the pacientes to set
     */
    public void setPacientes(List<DatosPaciente> pacientes) {
        this.pacientes = pacientes;
    }

    /**
     * @return the tiposRecetas
     */
    public List<TipoReceta> getTiposRecetas() {
        return tiposRecetas;
    }

    /**
     * @param tiposRecetas the tiposRecetas to set
     */
    public void setTiposRecetas(List<TipoReceta> tiposRecetas) {
        this.tiposRecetas = tiposRecetas;
    }

    public void cambioPaciente() {
        if (idPacienteSeleccionado != null && !idPacienteSeleccionado.equals("")) {
            System.out.println("el paciente es " + idPacienteSeleccionado);
            receta.setDatosPaciente(dao.getPaciente(Integer.parseInt(idPacienteSeleccionado)));
        }
        checarRecetaCompleta();
    }

    public void cambioReceta() {
        if (idTipoRecetaSeleccionado != null && !idTipoRecetaSeleccionado.equals("")) {
            System.out.println("la receta es " + idTipoRecetaSeleccionado);
            receta.setTipoReceta(dao.getTipoReceta(Integer.parseInt(idTipoRecetaSeleccionado)));
        }
        checarRecetaCompleta();
    }

    public void cambioContenido() {
        if (contenido != null && !contenido.equals("")) {
            System.out.println("el conte " + contenido);
            receta.setContenido(contenido);
        }
        checarRecetaCompleta();
    }

    /**
     * @return the idPacienteSeleccionado
     */
    public String getIdPacienteSeleccionado() {
        return idPacienteSeleccionado;
    }

    /**
     * @param idPacienteSeleccionado the idPacienteSeleccionado to set
     */
    public void setIdPacienteSeleccionado(String idPacienteSeleccionado) {
        this.idPacienteSeleccionado = idPacienteSeleccionado;
    }

    /**
     * @return the idTipoRecetaSeleccionado
     */
    public String getIdTipoRecetaSeleccionado() {
        return idTipoRecetaSeleccionado;
    }

    /**
     * @param idTipoRecetaSeleccionado the idTipoRecetaSeleccionado to set
     */
    public void setIdTipoRecetaSeleccionado(String idTipoRecetaSeleccionado) {
        this.idTipoRecetaSeleccionado = idTipoRecetaSeleccionado;
    }

    /**
     * @return the contenido
     */
    public String getContenido() {
        return contenido;
    }

    /**
     * @param contenido the contenido to set
     */
    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    /**
     * @return the recetaCompleta
     */
    public boolean isRecetaCompleta() {
        return recetaCompleta;
    }

    /**
     * @param recetaCompleta the recetaCompleta to set
     */
    public void setRecetaCompleta(boolean recetaCompleta) {
        this.recetaCompleta = recetaCompleta;
    }

    private void checarRecetaCompleta() {
        System.out.println("conte -" + contenido);
        System.out.println("datis -" + this.idPacienteSeleccionado);
        System.out.println("tipo -" + idTipoRecetaSeleccionado);
        if (receta.getContenido() != null && receta.getDatosPaciente() != null && receta.getTipoReceta() != null) {
            System.out.println("los datos estan completos");
            recetaCompleta = true;
            generarReceta();
            return;
        }
        System.out.println("tengo que dar el default");
        rutaPdf = Configurador.getCfg("rutaWeb") + "logo.pdf";
        recetaCompleta = false;
    }

    private void generarReceta() {
        System.out.println("empiezo a generar el pdf de la receta");
        receta.setFecha(Calendar.getInstance().getTime());
        List<String> contenidoListas = generarListaContenido();
        GeneradorRecetas generador = new GeneradorRecetas("tmp" + idPacienteSeleccionado + ".pdf", receta.getTipoReceta().getArchivo());
        String ruta = generador.generarReceta(contenidoListas, receta.getTipoReceta().getInicialX(), receta.getTipoReceta().getInicialY());
        System.out.println("la ruta del archivo es " + ruta);
        this.setRutaPdf(ruta);
    }

    private List<String> generarListaContenido() {
        List<String> contenidos = new LinkedList<String>();
        String original = receta.getContenido();
        String[] partidas = original.split("\n");
        for (String partido : partidas) {
            int longitudPermitida = 90;
            int indice1 = 0;
            int indice2 = longitudPermitida;
            while (indice1 != indice2) {
                indice2 = partido.length() > indice2 ? indice2 : partido.length();
                String pedazo = partido.substring(indice1, indice2);
                indice1 = indice2;
                indice2 = indice2 == partido.length() ? indice2 : indice2 + longitudPermitida;
                contenidos.add(pedazo);
            }
        }

        return contenidos;
    }

    public static void main(String[] args) {
        String cadena = "algo \n"
                + "           otro algo";
        String[] split = cadena.split("\n");
        System.out.println(cadena);
        System.out.println(split[0]);
    }

    /**
     * @return the rutaPdf
     */
    public String getRutaPdf() {
        return rutaPdf;
    }

    /**
     * @param rutaPdf the rutaPdf to set
     */
    public void setRutaPdf(String rutaPdf) {
        this.rutaPdf = rutaPdf;
    }

    public String guardarReceta() {
        if (recetaCompleta) {

            borrarArchivo(rutaPdf);
            Integer id = dao.guardar(receta);
            guardarPdf(id);
            return "recetas";
        }
        return null;
    }

    private void borrarArchivo(String rutaPdf) {
        File f=new File(rutaPdf);
        f.delete();
    }

    private void guardarPdf(Integer id) {
        List<String> contenidoListas = generarListaContenido();
        GeneradorRecetas generador = new GeneradorRecetas(id+".pdf", receta.getTipoReceta().getArchivo());
        String ruta = generador.generarReceta(contenidoListas, receta.getTipoReceta().getInicialX(), receta.getTipoReceta().getInicialY());
        
    }
}
