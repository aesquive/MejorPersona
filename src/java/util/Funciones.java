/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Alberto
 */
public class Funciones {

    public static Date formarDate(Calendar instance, int hrsDesayuno, int minsDesayuno) {
        instance.set(Calendar.HOUR_OF_DAY, hrsDesayuno);
        instance.set(Calendar.MINUTE, minsDesayuno);
        return instance.getTime();
    }

    public static String DateToString(Date fechaInicial) {
        Calendar instance = Calendar.getInstance();
        instance.setTime(fechaInicial);
        String dia=Funciones.getDia(instance.get(Calendar.DAY_OF_WEEK));
        String mes=Funciones.getMes(instance.get(Calendar.MONTH));
        String ano=String.valueOf(instance.get(Calendar.YEAR));
        return dia+" "+instance.get(Calendar.DAY_OF_MONTH) +" de "+mes+" del "+ano;
        
    }
    
    private static String getDia(int numero){
        switch(numero){
            case Calendar.SUNDAY:return "Domingo";
            case Calendar.MONDAY:return "Lunes";
            case Calendar.TUESDAY:return "Martes";
            case Calendar.WEDNESDAY:return "Miercoles";
            case Calendar.THURSDAY:return "Jueves";
            case Calendar.FRIDAY:return "Viernes";
            case Calendar.SATURDAY:return "Sabado";
        }
        return "";
    }
    
    private static String getMes(int numero){
        switch(numero){
            case Calendar.JANUARY:return "Enero";
            case Calendar.FEBRUARY:return "Febrero";
            case Calendar.MARCH:return "Marzo";
            case Calendar.APRIL:return "Abril";
            case Calendar.MAY:return "Mayo";
            case Calendar.JUNE:return "Junio";
            case Calendar.JULY:return "Julio";
            case Calendar.AUGUST:return "Agosto";
            case Calendar.SEPTEMBER:return "Septiembre";
            case Calendar.OCTOBER:return "Octubre";
            case Calendar.NOVEMBER:return "Noviembre";
            case Calendar.DECEMBER:return "Diciembre";
        }
        return "";
    }
    
    public static void main(String[] args) {
        Calendar instance = Calendar.getInstance();
        String DateToString = Funciones.sacarHorario(instance.getTime());
        System.out.println(DateToString);
    }

    public static String sacarHorario(Date fechaInicial) {
        Calendar instance = Calendar.getInstance();
        instance.setTime(fechaInicial);
        String amPm=instance.get(Calendar.AM_PM)==Calendar.AM ? "am" : "pm";
        int hora = instance.get(Calendar.HOUR);
        String hrSt=String.valueOf(hora).length()==1 ? "0"+String.valueOf(hora) : String.valueOf(hora);
        
        int min = instance.get(Calendar.MINUTE);
        String minS=String.valueOf(min).length()==1 ? "0"+String.valueOf(min) : String.valueOf(min);
        
        return hrSt+":"+minS+" "+amPm;
    }

    public static String truncar(int decimales, Double cantidad) {
        DecimalFormat format=new DecimalFormat("##.##");
        return format.format(cantidad);
    }
}
