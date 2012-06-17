package pojos;
// Generated 16/06/2012 12:08:15 AM by Hibernate Tools 3.2.1.GA


import java.util.HashSet;
import java.util.Set;

/**
 * TipoReceta generated by hbm2java
 */
public class TipoReceta  implements java.io.Serializable {


     private Integer idTipoReceta;
     private DatosDoctor datosDoctor;
     private String nombre;
     private String archivo;
     private Integer inicialX;
     private Integer inicialY;
     private Set recetas = new HashSet(0);

    public TipoReceta() {
    }

    public TipoReceta(DatosDoctor datosDoctor, String nombre, String archivo, Integer inicialX, Integer inicialY, Set recetas) {
       this.datosDoctor = datosDoctor;
       this.nombre = nombre;
       this.archivo = archivo;
       this.inicialX = inicialX;
       this.inicialY = inicialY;
       this.recetas = recetas;
    }
   
    public Integer getIdTipoReceta() {
        return this.idTipoReceta;
    }
    
    public void setIdTipoReceta(Integer idTipoReceta) {
        this.idTipoReceta = idTipoReceta;
    }
    public DatosDoctor getDatosDoctor() {
        return this.datosDoctor;
    }
    
    public void setDatosDoctor(DatosDoctor datosDoctor) {
        this.datosDoctor = datosDoctor;
    }
    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getArchivo() {
        return this.archivo;
    }
    
    public void setArchivo(String archivo) {
        this.archivo = archivo;
    }
    public Integer getInicialX() {
        return this.inicialX;
    }
    
    public void setInicialX(Integer inicialX) {
        this.inicialX = inicialX;
    }
    public Integer getInicialY() {
        return this.inicialY;
    }
    
    public void setInicialY(Integer inicialY) {
        this.inicialY = inicialY;
    }
    public Set getRecetas() {
        return this.recetas;
    }
    
    public void setRecetas(Set recetas) {
        this.recetas = recetas;
    }




}


