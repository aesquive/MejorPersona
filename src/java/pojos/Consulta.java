package pojos;
// Generated 25/06/2012 06:37:10 PM by Hibernate Tools 3.2.1.GA


import java.util.Date;

/**
 * Consulta generated by hbm2java
 */
public class Consulta  implements java.io.Serializable {


     private Integer idConsulta;
     private DatosDoctor datosDoctor;
     private DatosPaciente datosPaciente;
     private Date fecha;

    public Consulta() {
    }

    public Consulta(DatosDoctor datosDoctor, DatosPaciente datosPaciente, Date fecha) {
       this.datosDoctor = datosDoctor;
       this.datosPaciente = datosPaciente;
       this.fecha = fecha;
    }
   
    public Integer getIdConsulta() {
        return this.idConsulta;
    }
    
    public void setIdConsulta(Integer idConsulta) {
        this.idConsulta = idConsulta;
    }
    public DatosDoctor getDatosDoctor() {
        return this.datosDoctor;
    }
    
    public void setDatosDoctor(DatosDoctor datosDoctor) {
        this.datosDoctor = datosDoctor;
    }
    public DatosPaciente getDatosPaciente() {
        return this.datosPaciente;
    }
    
    public void setDatosPaciente(DatosPaciente datosPaciente) {
        this.datosPaciente = datosPaciente;
    }
    public Date getFecha() {
        return this.fecha;
    }
    
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }




}


