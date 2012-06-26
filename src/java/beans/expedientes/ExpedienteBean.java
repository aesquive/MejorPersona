/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans.expedientes;

import beans.Bean;
import configuradores.Configurador;
import documentadores.expedientes.GeneradorExpedientes;
import guardadores.GuardadorExpediente;
import java.util.Calendar;
import java.util.Date;
import mail.EnviadorCorreos;
import org.primefaces.event.CaptureEvent;
import org.primefaces.event.FlowEvent;
import pojos.DatosPaciente;
import pojos.ExpedienteComidas;
import pojos.ExpedienteDatos;
import pojos.ExpedienteMatriz;
import util.Funciones;

/**
 *
 * @author Alberto
 */
public class ExpedienteBean {

    private DatosPaciente datosPaciente;
    private ExpedienteDatos expedienteDatos;
    private ExpedienteComidas expedienteComidas;
    private ExpedienteMatriz expedienteMatriz;
    
    private int hrsDesayuno;
    private int minsDesayuno;
    private int hrsComida;
    private int minsComida;
    private int hrsCena;
    private int minsCena;
    
    private Date fechaMinima;
    
    public ExpedienteBean() {
        ponerFechaMinima();
        this.hrsDesayuno=12;
        this.hrsComida=12;
        this.hrsCena=12;
        datosPaciente = new DatosPaciente();
        expedienteDatos = new ExpedienteDatos();
        expedienteComidas = new ExpedienteComidas();
        expedienteMatriz=new ExpedienteMatriz();
    }

    
    public String onFlowProcess(FlowEvent event) {
        return event.getNewStep();
    }

    /**
     * @return the datosPaciente
     */
    public DatosPaciente getDatosPaciente() {
        return datosPaciente;
    }

    /**
     * @param datosPaciente the datosPaciente to set
     */
    public void setDatosPaciente(DatosPaciente datosPaciente) {
        this.datosPaciente = datosPaciente;
    }

    /**
     * @return the expedienteDatos
     */
    public ExpedienteDatos getExpedienteDatos() {
        return expedienteDatos;
    }

    /**
     * @param expedienteDatos the expedienteDatos to set
     */
    public void setExpedienteDatos(ExpedienteDatos expedienteDatos) {
        this.expedienteDatos = expedienteDatos;
    }

    /**
     * @return the expedienteComidas
     */
    public ExpedienteComidas getExpedienteComidas() {
        return expedienteComidas;
    }

    /**
     * @param expedienteComidas the expedienteComidas to set
     */
    public void setExpedienteComidas(ExpedienteComidas expedienteComidas) {
        this.expedienteComidas = expedienteComidas;
    }
    
    public String guardar(){
        generarClavePaciente();
        datosPaciente.setTema("cupertino");
        inyectarPaciente();
        ponerHorasComidas();
        GuardadorExpediente guardador=new GuardadorExpediente(this.datosPaciente,this.expedienteComidas,this.expedienteDatos,this.expedienteMatriz);
        guardador.guardar();
        ponerBeanVista();
        enviarCorreo(datosPaciente);
        return "vista";
    }

    /**
     * @return the hrsDesayuno
     */
    public int getHrsDesayuno() {
        return hrsDesayuno;
    }

    /**
     * @param hrsDesayuno the hrsDesayuno to set
     */
    public void setHrsDesayuno(int hrsDesayuno) {
        this.hrsDesayuno = hrsDesayuno;
    }

    /**
     * @return the minsDesayuno
     */
    public int getMinsDesayuno() {
        return minsDesayuno;
    }

    /**
     * @param minsDesayuno the minsDesayuno to set
     */
    public void setMinsDesayuno(int minsDesayuno) {
        this.minsDesayuno = minsDesayuno;
    }

    /**
     * @return the hrsComida
     */
    public int getHrsComida() {
        return hrsComida;
    }

    /**
     * @param hrsComida the hrsComida to set
     */
    public void setHrsComida(int hrsComida) {
        this.hrsComida = hrsComida;
    }

    /**
     * @return the minsComida
     */
    public int getMinsComida() {
        return minsComida;
    }

    /**
     * @param minsComida the minsComida to set
     */
    public void setMinsComida(int minsComida) {
        this.minsComida = minsComida;
    }

    /**
     * @return the hrsCena
     */
    public int getHrsCena() {
        return hrsCena;
    }

    /**
     * @param hrsCena the hrsCena to set
     */
    public void setHrsCena(int hrsCena) {
        this.hrsCena = hrsCena;
    }

    /**
     * @return the minsCena
     */
    public int getMinsCena() {
        return minsCena;
    }

    /**
     * @param minsCena the minsCena to set
     */
    public void setMinsCena(int minsCena) {
        this.minsCena = minsCena;
    }

    private void ponerHorasComidas() {
        expedienteComidas.setHoraDesayuno(Funciones.formarDate(Calendar.getInstance() , hrsDesayuno,minsDesayuno));
        expedienteComidas.setHoraComida(Funciones.formarDate(Calendar.getInstance() , hrsComida,minsComida));
        expedienteComidas.setHoraCena(Funciones.formarDate(Calendar.getInstance() , hrsCena,minsCena));
    }

    private void inyectarPaciente() {
        expedienteComidas.setDatosPaciente(datosPaciente);
        expedienteDatos.setDatosPaciente(datosPaciente);
        expedienteMatriz.setDatosPaciente(datosPaciente);
    }

    /**
     * @return the expedienteMatriz
     */
    public ExpedienteMatriz getExpedienteMatriz() {
        return expedienteMatriz;
    }

    /**
     * @param expedienteMatriz the expedienteMatriz to set
     */
    public void setExpedienteMatriz(ExpedienteMatriz expedienteMatriz) {
        this.expedienteMatriz = expedienteMatriz;
    }

    private void ponerBeanVista() {
        GeneradorExpedientes generador=new GeneradorExpedientes(datosPaciente.getIdPaciente(), datosPaciente, expedienteDatos, expedienteComidas, expedienteMatriz);
        String archivo = Configurador.getCfg("rutaWeb")+"expedientes"+Configurador.getCfg("delimitador")+generador.generar();
        Bean.ponerBean("vistaExpedienteBean", new VistaExpedienteBean(archivo));
    }

    private void generarClavePaciente() {
        String apePat = datosPaciente.getApePat();
        String apeMat = datosPaciente.getApeMat();
        String nom = datosPaciente.getNom();
        String primeras=apePat.substring(0, 1)+apePat.substring(apePat.length()-1,apePat.length());
        String segundas=apeMat.substring(0, 1)+apeMat.substring(apeMat.length()-1,apeMat.length());
        String terc=nom.substring(0, 1)+nom.substring(nom.length()-1,nom.length());
        String usuario=primeras+segundas+terc;
        datosPaciente.setUsuario(usuario.toUpperCase());
        datosPaciente.setPassword(usuario.toUpperCase());
        
    }
    
    /**
     * @return the fechaMinima
     */
    public Date getFechaMinima() {
        return fechaMinima;
    }

    /**
     * @param fechaMinima the fechaMinima to set
     */
    public void setFechaMinima(Date fechaMinima) {
        this.fechaMinima = fechaMinima;
    }

    private void ponerFechaMinima() {
        Calendar instance = Calendar.getInstance();
        instance.set(Calendar.YEAR, instance.get(Calendar.YEAR)-90);
        System.out.println(instance.get(Calendar.YEAR));
        fechaMinima=instance.getTime();
    }


    public static void main(String[] args) {
        Calendar instance = Calendar.getInstance();
        instance.set(Calendar.YEAR, instance.get(Calendar.YEAR)-100);
        System.out.println(instance.get(Calendar.YEAR));
    }

    private void enviarCorreo(DatosPaciente datosPaciente) {
        String[] mensaje = formarMensaje(datosPaciente);
        EnviadorCorreos enviador = new EnviadorCorreos(Configurador.getCfg("enviadorCorreoSmtp"), Configurador.getCfg("enviadorCorreoRemitente"), Configurador.getCfg("enviadorCorreoPassword"), Configurador.getCfg("enviadorCorreoPuerto"));
        enviador.enviar(new String[]{datosPaciente.getCorreo()}, "BIENVENIDO A MEJOR PERSONA", mensaje);
    }

    private String[] formarMensaje(DatosPaciente datosPaciente) {
        String saludo = "Estimado(a) " + datosPaciente.getNom() + " " + datosPaciente.getApePat();
        String cuerpo = "Agradeciendo su preferencia e informandole que desde este momento usted tiene acceso a toda su informacion ";
        String cuerpo2="a traves de la pagina de internet con los siguientes datos";
        String cuerpo3="Usuario:"+datosPaciente.getUsuario();
        String cuerpo4="Password:"+datosPaciente.getPassword();
        String despedida = "Saludos";
        return new String[]{saludo, cuerpo+cuerpo2,cuerpo3,cuerpo4, despedida};
    }

}
