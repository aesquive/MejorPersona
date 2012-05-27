package pojos;
// Generated 24/05/2012 08:46:41 PM by Hibernate Tools 3.2.1.GA


import java.util.HashSet;
import java.util.Set;

/**
 * DatosPaciente generated by hbm2java
 */
public class DatosPaciente  implements java.io.Serializable {


     private Integer idPaciente;
     private String apePat;
     private String apeMat;
     private String nom;
     private String segNom;
     private String tel;
     private String cel;
     private String domicilio;
     private String correo;
     private byte[] foto;
     private Set expedienteDatoses = new HashSet(0);
     private Set consultas = new HashSet(0);
     private Set expedienteMatrizs = new HashSet(0);
     private Set citas = new HashSet(0);
     private Set expedienteComidases = new HashSet(0);

    public DatosPaciente() {
    }

    public DatosPaciente(String apePat, String apeMat, String nom, String segNom, String tel, String cel, String domicilio, String correo, byte[] foto, Set expedienteDatoses, Set consultas, Set expedienteMatrizs, Set citas, Set expedienteComidases) {
       this.apePat = apePat;
       this.apeMat = apeMat;
       this.nom = nom;
       this.segNom = segNom;
       this.tel = tel;
       this.cel = cel;
       this.domicilio = domicilio;
       this.correo = correo;
       this.foto = foto;
       this.expedienteDatoses = expedienteDatoses;
       this.consultas = consultas;
       this.expedienteMatrizs = expedienteMatrizs;
       this.citas = citas;
       this.expedienteComidases = expedienteComidases;
    }
   
    public Integer getIdPaciente() {
        return this.idPaciente;
    }
    
    public void setIdPaciente(Integer idPaciente) {
        this.idPaciente = idPaciente;
    }
    public String getApePat() {
        return this.apePat;
    }
    
    public void setApePat(String apePat) {
        this.apePat = apePat;
    }
    public String getApeMat() {
        return this.apeMat;
    }
    
    public void setApeMat(String apeMat) {
        this.apeMat = apeMat;
    }
    public String getNom() {
        return this.nom;
    }
    
    public void setNom(String nom) {
        this.nom = nom;
    }
    public String getSegNom() {
        return this.segNom;
    }
    
    public void setSegNom(String segNom) {
        this.segNom = segNom;
    }
    public String getTel() {
        return this.tel;
    }
    
    public void setTel(String tel) {
        this.tel = tel;
    }
    public String getCel() {
        return this.cel;
    }
    
    public void setCel(String cel) {
        this.cel = cel;
    }
    public String getDomicilio() {
        return this.domicilio;
    }
    
    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }
    public String getCorreo() {
        return this.correo;
    }
    
    public void setCorreo(String correo) {
        this.correo = correo;
    }
    public byte[] getFoto() {
        return this.foto;
    }
    
    public void setFoto(byte[] foto) {
        this.foto = foto;
    }
    public Set getExpedienteDatoses() {
        return this.expedienteDatoses;
    }
    
    public void setExpedienteDatoses(Set expedienteDatoses) {
        this.expedienteDatoses = expedienteDatoses;
    }
    public Set getConsultas() {
        return this.consultas;
    }
    
    public void setConsultas(Set consultas) {
        this.consultas = consultas;
    }
    public Set getExpedienteMatrizs() {
        return this.expedienteMatrizs;
    }
    
    public void setExpedienteMatrizs(Set expedienteMatrizs) {
        this.expedienteMatrizs = expedienteMatrizs;
    }
    public Set getCitas() {
        return this.citas;
    }
    
    public void setCitas(Set citas) {
        this.citas = citas;
    }
    public Set getExpedienteComidases() {
        return this.expedienteComidases;
    }
    
    public void setExpedienteComidases(Set expedienteComidases) {
        this.expedienteComidases = expedienteComidases;
    }




}


