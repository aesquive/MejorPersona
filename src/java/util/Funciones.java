/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

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
    
}
