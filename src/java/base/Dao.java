/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package base;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import pojos.DatosPaciente;

/**
 *
 * @author Alberto
 */
public class Dao {

    private static Session sesion;

    public Dao() {
        sesion = sesion == null ? HibernateUtil.getSessionFactory().openSession() : sesion;
    }

    public void guardarActualizar(Object objeto) {
        Transaction trans = sesion.beginTransaction();
        sesion.saveOrUpdate(objeto);
        trans.commit();
    }

    public List getTabla(Class clase) {
        return sesion.createCriteria(clase).list();
    }

    public List queryCriterion(Class clase, Criterion criterio) {
        return sesion.createCriteria(clase).add(criterio).list();
    }
    
    public List queryComun(String query){
        return sesion.createQuery(query).list();
    }

    public static void main(String[] args) {
        Dao dao = new Dao();
        
//        
//        System.out.println("Ejemplo 0");
//        List<DatosPaciente> queryComun = dao.queryComun("from DatosPaciente");
//        for(DatosPaciente dat:queryComun){
//            System.out.println(dat);
//        }
//        
//        System.out.println("Ejemplo 1");
//        //ejemplo para sacar la tabla de pacientes
//        List<DatosPaciente> tabla = dao.getTabla(DatosPaciente.class);
//        for (DatosPaciente dat : tabla) {
//            System.out.println(dat);
//        }
//        
//        System.out.println("Ejemplo 2");
//        //ejemplo para sacar un registro con criterion
//        List<DatosPaciente> queryCriterion = dao.queryCriterion(DatosPaciente.class,Restrictions.eq("id", 4));
//        for(DatosPaciente d:queryCriterion){
//            System.out.println(d);
//        }
        
        //ejemplo para sacar un registro con like(Strings)
//        System.out.println("Ejemplo 3");
//        List<DatosPaciente> queryLike = dao.queryCriterion(DatosPaciente.class, Restrictions.ilike("apePat","Esq%"));
//        for(DatosPaciente dat:queryLike){
//            System.out.println(dat);
//        }
        
        DatosPaciente paciente=new DatosPaciente();
        paciente.setApePat("Garcia");
        paciente.setApeMat("Garcia");
        paciente.setNom("Javier");
        dao.guardarActualizar(paciente);
    }
}
