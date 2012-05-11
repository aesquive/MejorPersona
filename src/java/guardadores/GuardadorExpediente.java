/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package guardadores;

import base.Dao;

/**
 *
 * @author Alberto
 */
public class GuardadorExpediente {

    private Object[] datos;
    
    public GuardadorExpediente(Object... datos) {
        this.datos=datos;
    }
    
    public void guardar(){
        Dao dao=new Dao();
        for(Object o:datos){
            dao.guardarActualizar(o);
        }
        return;
    }
}
