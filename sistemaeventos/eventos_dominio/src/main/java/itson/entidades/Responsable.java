package itson.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import itson.enums.TipoResponsable;

/**
 * Representa un responsable dentro del sistema de eventos.
 * Un responsable puede ser organizador, ponente u otro tipo definido en {@link TipoResponsable}.
 * Contiene información personal y de contacto.
 */
@Entity
@Table(name = "responsables")
public class Responsable {

    /**
     * Identificador único del responsable.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * Nombre del responsable.
     */
    @Column(name = "nombre", length = 50, nullable = false)
    private String nombre;

    /**
     * Apellido paterno del responsable.
     */
    @Column(name = "apellido_paterno", length = 50, nullable = false)
    private String apellidoPaterno;

    /**
     * Apellido materno del responsable.
     */
    @Column(name = "apellido_materno", length = 50, nullable = false)
    private String apellidoMaterno;

    /**
     * Teléfono del responsable.
     */
    @Column(name = "telefono", length = 50, nullable = false)
    private String telefono;

    /**
     * Tipo de responsable (RESPONSABLE, ORGANIZADOR o PONENTE).
     */
    @Column(name = "tipo_responsable", nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoResponsable tipoResponsable;

    /**
     * Constructor por defecto.
     */
    public Responsable() {}

    /**
     * Constructor con parámetros.
     *
     * @param nombre Nombre del responsable.
     * @param apellidoPaterno Apellido paterno del responsable.
     * @param apellidoMaterno Apellido materno del responsable.
     * @param telefono Teléfono del responsable.
     * @param tipoResponsable Tipo de responsable (RESPONSABLE, ORGANIZADOR o PONENTE).
     */
    public Responsable(String nombre, String apellidoPaterno, String apellidoMaterno, String telefono, TipoResponsable tipoResponsable) {
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.telefono = telefono;
        this.tipoResponsable = tipoResponsable;
    }

    /**
     * Obtiene el identificador único del responsable.
     * @return El identificador del responsable.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Establece el identificador único del responsable.
     * @param id El identificador del responsable.
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Obtiene el nombre del responsable.
     * @return El nombre del responsable.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del responsable.
     * @param nombre El nombre del responsable.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene el apellido paterno del responsable.
     * @return El apellido paterno del responsable.
     */
    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    /**
     * Establece el apellido paterno del responsable.
     * @param apellidoPaterno El apellido paterno del responsable.
     */
    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    /**
     * Obtiene el apellido materno del responsable.
     * @return El apellido materno del responsable.
     */
    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    /**
     * Establece el apellido materno del responsable.
     * @param apellidoMaterno El apellido materno del responsable.
     */
    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    /**
     * Obtiene el teléfono del responsable.
     * @return El teléfono del responsable.
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * Establece el teléfono del responsable.
     * @param telefono El teléfono del responsable.
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /**
     * Obtiene el tipo de responsable.
     * @return El tipo de responsable.
     */
    public TipoResponsable getTipoResponsable() {
        return tipoResponsable;
    }

    /**
     * Establece el tipo de responsable.
     * @param tipoResponsable El tipo de responsable.
     */
    public void setTipoResponsable(TipoResponsable tipoResponsable) {
        this.tipoResponsable = tipoResponsable;
    }

    /**
     * Devuelve una representación en cadena del responsable.
     * @return Una cadena con los datos del responsable.
     */
    @Override
    public String toString() {
        return "Responsable{" +
                "id='" + id + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellidoPaterno='" + apellidoPaterno + '\'' +
                ", apellidoMaterno='" + apellidoMaterno + '\'' +
                ", telefono='" + telefono + '\'' +
                ", tipoResponsable=" + tipoResponsable +
                '}';
    }
}