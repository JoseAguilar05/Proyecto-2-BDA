package itson.dtos;

import itson.enums.TipoResponsable;

public class ResponsableDTO {
    private Integer id;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String telefono;
    private TipoResponsable tipoResponsable;

    /**
     * Constructor de la clase ResponsableDTO.
     * @param id El identificador único del responsable.
     * @param nombre El nombre del responsable.
     * @param apellidoPaterno El apellido paterno del responsable.
     * @param apellidoMaterno El apellido materno del responsable.
     * @param telefono El número de teléfono del responsable.
     * @param tipoResponsable El tipo de responsable (Ejecutivo, Coordinador, etc.).
     */
    public ResponsableDTO(Integer id, String nombre, String apellidoPaterno, String apellidoMaterno, String telefono, TipoResponsable tipoResponsable) {
        this.id = id;
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.telefono = telefono;
        this.tipoResponsable = tipoResponsable;
    }

    public Integer getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public String getTelefono() {
        return telefono;
    }

    public TipoResponsable getTipoResponsable() {
        return tipoResponsable;
    }
}