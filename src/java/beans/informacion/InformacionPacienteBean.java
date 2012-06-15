package beans.informacion;

import base.Dao;
import beans.Bean;
import beans.login.LoginBean;
import configuradores.Configurador;
import documentadores.GeneradorExpedientes;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.event.AjaxBehaviorEvent;
import pojos.DatosPaciente;
import pojos.ExpedienteComidas;
import pojos.ExpedienteDatos;
import pojos.ExpedienteMatriz;
import util.Vector;

/**
 *
 * @author alberto
 */
public class InformacionPacienteBean {

    private final String rutaServidor = Configurador.getCfg("rutaWeb");
    private final String ruta = Configurador.getCfg("rutaLocal");
    private final String delimitador=Configurador.getCfg("delimitador");
    
    private String idPacienteElegido;
    private DatosPaciente pacienteActual;
    private List<DatosPaciente> listaPacientes;
    private List<Vector<String, String>> archivos;
    private Dao dao;
    private String rutaPdfActual;
    private LoginBean loginBean;

    
    
    public InformacionPacienteBean() {
        dao = new Dao();
        loginBean = (LoginBean) Bean.getBean("loginBean");
        generarListaPacientes();
        rutaPdfActual = rutaServidor + "logo.pdf";
    }

    private void generarListaPacientes() {
        if (loginBean.getDatosDoctor() != null) {
            setListaPacientes((List<DatosPaciente>) dao.getTabla(DatosPaciente.class));
            Collections.sort(listaPacientes);
            return;
        }
        if (loginBean.getDatosPaciente() != null) {
            listaPacientes = new LinkedList<DatosPaciente>();
            listaPacientes.add(loginBean.getDatosPaciente());
            this.idPacienteElegido = loginBean.getDatosPaciente().getIdPaciente().toString();
            this.pacienteActual = dao.getPaciente(Integer.parseInt(idPacienteElegido));
            generarArchivos();
        }
    }

    private void generarArchivos() {

        archivos = new LinkedList<Vector<String, String>>();
        if (this.getPacienteActual() == null) {
            return;
        }
        String rutaExpediente = obtenerRutaExpediente(ruta + "expedientes"+delimitador);
        getArchivos().add(new Vector<String, String>("Expediente", rutaServidor + "expedientes"+delimitador + rutaExpediente));

        this.rutaPdfActual = archivos.get(0).getY();
    }

    private String obtenerRutaExpediente(String rutaExp) {
        try {
            FileReader reader = new FileReader(rutaExp + getPacienteActual().getIdPaciente() + ".pdf");
            reader.close();
            return getPacienteActual().getIdPaciente() + ".pdf";
        } catch (IOException ex) {
            System.out.println("no existe expediente , generandolo....");
            GeneradorExpedientes generador = new GeneradorExpedientes(getPacienteActual().getIdPaciente(), getPacienteActual(),
                    (ExpedienteDatos) getPacienteActual().getExpedienteDatoses().iterator().next(), (ExpedienteComidas) getPacienteActual().getExpedienteComidases().iterator().next(), (ExpedienteMatriz) getPacienteActual().getExpedienteMatrizs().iterator().next());
            String generar = generador.generar();
            return generar;
        }
    }

    public static void main(String[] args) {
        try {
            FileReader reader = new FileReader("/var/www/private/expedientes/1.pdf");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(InformacionPacienteBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @return the pacienteActual
     */
    public DatosPaciente getPacienteActual() {
        return pacienteActual;
    }

    /**
     * @param pacienteActual the pacienteActual to set
     */
    public void setPacienteActual(DatosPaciente pacienteActual) {
        System.out.println("haciendo set aqui");
        this.pacienteActual = pacienteActual;
    }

    /**
     * @return the listaPacientes
     */
    public List<DatosPaciente> getListaPacientes() {
        return listaPacientes;
    }

    /**
     * @param listaPacientes the listaPacientes to set
     */
    public void setListaPacientes(List<DatosPaciente> listaPacientes) {
        this.listaPacientes = listaPacientes;
    }

    /**
     * @return the rutaPdfActual
     */
    public String getRutaPdfActual() {
        return rutaPdfActual;
    }

    /**
     * @param rutaPdfActual the rutaPdfActual to set
     */
    public void setRutaPdfActual(String rutaPdfActual) {
        this.rutaPdfActual = rutaPdfActual;
    }

    /**
     * @return the archivos
     */
    public List<Vector<String, String>> getArchivos() {
        return archivos;
    }

    /**
     * @param archivos the archivos to set
     */
    public void setArchivos(List<Vector<String, String>> archivos) {
        this.archivos = archivos;
    }

    /**
     * @return the idPacienteElegido
     */
    public String getIdPacienteElegido() {
        return idPacienteElegido;
    }

    /**
     * @param idPacienteElegido the idPacienteElegido to set
     */
    public void setIdPacienteElegido(String idPacienteElegido) {
        this.idPacienteElegido = idPacienteElegido;
    }

    public void cambioArchivo() {
        System.out.println("cambiando el archivo");
    }

    public void cambioPaciente() {
        this.rutaPdfActual = rutaServidor + "logo.pdf";
        if (getIdPacienteElegido() != null && !idPacienteElegido.equals("")) {
            this.pacienteActual = dao.getPaciente(Integer.parseInt(getIdPacienteElegido()));
            System.out.println("el idelegido es " + getIdPacienteElegido());
            System.out.println("puse ya al paciente actual");
            generarArchivos();
            System.out.println("los archivos son " + archivos.size());
            return;
        }
        archivos = new LinkedList<Vector<String, String>>();

    }
}
