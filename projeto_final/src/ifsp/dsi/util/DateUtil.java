/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifsp.dsi.util;

/**
 *
 * @author repolho
 */
public class DateUtil {
    
    public static java.sql.Date getDataAtualSQL() {
        java.util.Date today = new java.util.Date();
        return new java.sql.Date(today.getTime());
    }
    
    public static java.sql.Date convertToSQL(java.util.Calendar date) {
        return new java.sql.Date(date.getTimeInMillis());
    }
    
    public static java.util.Calendar convertToDate(java.sql.Date date) {
        java.util.Calendar calendar = java.util.Calendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }
    
}
