/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans.config;

import base.Dao;
import beans.Bean;
import beans.login.LoginBean;
import pojos.DatosDoctor;
import pojos.DatosPaciente;

/**
 *
 * @author alberto
 */
public class ConfiguracionUsuario {

    private String password;
    private String repPassword;
    private String theme;
    private LoginBean loginBean;

    public ConfiguracionUsuario() {
        this.loginBean = (LoginBean) Bean.getBean("loginBean");
        theme = loginBean.getTheme();
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the repPassword
     */
    public String getRepPassword() {
        return repPassword;
    }

    /**
     * @param repPassword the repPassword to set
     */
    public void setRepPassword(String repPassword) {
        this.repPassword = repPassword;
    }

    /**
     * @return the theme
     */
    public String getTheme() {
        return theme;
    }

    /**
     * @param theme the theme to set
     */
    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String guardarPassword() {
        DatosDoctor datosDoctor = loginBean.getDatosDoctor();
        DatosPaciente datosPaciente = loginBean.getDatosPaciente();
        if (datosDoctor != null) {
            guardarDoctor(datosDoctor, true);
            return "configuracion";
        }
        guardarPaciente(datosPaciente, true);
        return "configuracion";
    }

    public String guardarTema() {
        loginBean.setTheme(theme);
        DatosDoctor datosDoctor = loginBean.getDatosDoctor();
        DatosPaciente datosPaciente = loginBean.getDatosPaciente();
        if (datosDoctor != null) {
            guardarDoctor(datosDoctor, false);
            return "configuracion";
        }
        guardarPaciente(datosPaciente, false);
        return "configuracion";
    }

    private void guardarPaciente(DatosPaciente datosPaciente, boolean pas) {
        Dao dao = new Dao();
        if (pas) {
            if (password.equals(repPassword)) {

                datosPaciente.setPassword(password);
                
                loginBean.setMostrarMensaje(true);
                loginBean.setMensaje("Cambio Realizado");
            }else{
                loginBean.setMostrarMensaje(true);
                loginBean.setMensaje("Los passwords deben ser iguales");
            }
        } else {
            datosPaciente.setTema(theme);

        }
        dao.actualizar(datosPaciente);
        dao.refrescarPaciente(datosPaciente);
    }

    private void guardarDoctor(DatosDoctor datosDoctor, boolean pas) {
        Dao dao = new Dao();
        if (pas) {
            if (password.equals(repPassword)) {

                datosDoctor.setPas(password);
                loginBean.setMostrarMensaje(true);
                loginBean.setMensaje("Cambio Realizado");
            }
        } else {
            datosDoctor.setTema(theme);

        }
        dao.actualizar(datosDoctor);
        dao.refrescarPaciente(datosDoctor);
    }
}
