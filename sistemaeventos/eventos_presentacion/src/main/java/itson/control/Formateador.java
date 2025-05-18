package itson.control;

import java.time.LocalDate;
import java.util.Calendar;

public class Formateador {
    
    public static String formatearNombre(String nombre, String apellidoPaterno, String apellidoMateno){
        StringBuilder sb = new StringBuilder();
        sb.append(nombre);
        sb.append(" ");
        sb.append(apellidoPaterno);
        sb.append(" ");
        sb.append(apellidoMateno);
        return sb.toString();
    }

    public static String formatearCap(String texto) {
        StringBuilder sb = new StringBuilder();
        sb.append(texto.substring(0, 1).toUpperCase());
        sb.append(texto.substring(1).toLowerCase());
        return sb.toString();
    }

    public static String formatearFecha(Calendar fecha) {
        StringBuilder sb = new StringBuilder();
        sb.append(fecha.get(Calendar.YEAR));
        sb.append("/");
        sb.append(fecha.get(Calendar.MONTH) + 1);
        sb.append("/");
        sb.append(fecha.get(Calendar.DAY_OF_MONTH));
        return sb.toString();
    }
    
    public static Calendar toCalendar(LocalDate fecha) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(fecha.getYear(), fecha.getMonthValue() - 1, fecha.getDayOfMonth());
        return calendar;
    }

}
