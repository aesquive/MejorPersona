/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans.expedientes;

import beans.Bean;
import guardadores.GuardadorExpediente;
import java.util.Calendar;
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
    
    public ExpedienteBean() {
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
        inyectarPaciente();
        ponerHorasComidas();
        GuardadorExpediente guardador=new GuardadorExpediente(this.datosPaciente,this.expedienteComidas,this.expedienteDatos,this.expedienteMatriz);
        guardador.guardar();
        ponerBeanVista();
        return "vistaExpediente";
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
        Bean.ponerBean("vistaExpedienteBean", new VistaExpedienteBean(this));
    }
}
