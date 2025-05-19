package itson.reportes.beans;

public class ListaAsistencia {
    private String nombre;
    private String correo;

    public ListaAsistencia(){}

    public ListaAsistencia(String nombre, String correo) {
        this.nombre = nombre;
        this.correo = correo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

}
