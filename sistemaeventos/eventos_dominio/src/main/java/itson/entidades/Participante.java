package itson.entidades;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import itson.enums.TipoParticipante;

/**
 * Representa un participante en el sistema de eventos.
 * Esta clase contiene información personal y profesional de un participante.
 * Es una entidad mapeada a la tabla "participantes" en la base de datos.
 */
@Entity
@Table(name = "participantes")
public class Participante implements Serializable {

    /**
     * Identificador único del participante.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_participante")
    private Integer id;

    /**
     * Nombre del participante.
     */
    @Column(name = "nombre")
    private String nombre;

    /**
     * Apellido paterno del participante.
     */
    @Column(name = "apellido_paterno")
    private String apellidoPaterno;

    /**
     * Apellido materno del participante.
     */
    @Column(name = "apellido_materno")
    private String apellidoMaterno;

    /**
     * Correo electrónico del participante.
     */
    @Column(name = "correo")
    private String correo;

    /**
     * Teléfono del participante.
     */
    @Column(name = "telefono")
    private String telefono;

    /**
     * Tipo de participante (por ejemplo, estudiante, profesor, etc.).
     */
    @Column(name = "tipo_participante")
    private TipoParticipante tipoParticipante;

    /**
     * Dependencia a la que pertenece el participante.
     */
    @Column(name = "dependencia")
    private String dependencia;

    /**
     * Número de control del participante (si aplica).
     */
    @Column(name = "numero_control")
    private String numeroControl;

    /**
     * Lista de actividades en las que participa el participante.
     */
    private List<Actividad> actividades;

    /**
     * Constructor vacío requerido por JPA.
     */
    public Participante() {
    }

    /**
     * Constructor con parámetros para inicializar un participante.
     * 
     * @param id               Identificador único del participante.
     * @param nombre           Nombre del participante.
     * @param apellidoPaterno  Apellido paterno del participante.
     * @param apellidoMaterno  Apellido materno del participante.
     * @param correo           Correo electrónico del participante.
     * @param telefono         Teléfono del participante.
     * @param tipoParticipante Tipo de participante.
     * @param dependencia      Dependencia del participante.
     * @param numeroControl    Número de control del participante.
     */
    public Participante(Integer id, String nombre, String apellidoPaterno, String apellidoMaterno, String correo,
            String telefono, TipoParticipante tipoParticipante, String dependencia, String numeroControl) {
        this.id = id;
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.correo = correo;
        this.telefono = telefono;
        this.tipoParticipante = tipoParticipante;
        this.dependencia = dependencia;
        this.numeroControl = numeroControl;
    }

    // Métodos getter y setter con sus respectivas descripciones

    /**
     * Obtiene el identificador único del participante.
     * 
     * @return El identificador del participante.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Establece el identificador único del participante.
     * 
     * @param id El identificador del participante.
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Obtiene el nombre del participante.
     * 
     * @return El nombre del participante.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del participante.
     * 
     * @param nombre El nombre del participante.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene el apellido paterno del participante.
     * 
     * @return El apellido paterno del participante.
     */
    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    /**
     * Establece el apellido paterno del participante.
     * 
     * @param apellidoPaterno El apellido paterno del participante.
     */
    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    /**
     * Obtiene el apellido materno del participante.
     * 
     * @return El apellido materno del participante.
     */
    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    /**
     * Establece el apellido materno del participante.
     * 
     * @param apellidoMaterno El apellido materno del participante.
     */
    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    /**
     * Obtiene el correo electrónico del participante.
     * 
     * @return El correo electrónico del participante.
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * Establece el correo electrónico del participante.
     * 
     * @param correo El correo electrónico del participante.
     */
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    /**
     * Obtiene el teléfono del participante.
     * 
     * @return El teléfono del participante.
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * Establece el teléfono del participante.
     * 
     * @param telefono El teléfono del participante.
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /**
     * Obtiene el tipo de participante.
     * 
     * @return El tipo de participante.
     */
    public TipoParticipante getTipoParticipante() {
        return tipoParticipante;
    }

    /**
     * Establece el tipo de participante.
     * 
     * @param tipoParticipante El tipo de participante.
     */
    public void setTipoParticipante(TipoParticipante tipoParticipante) {
        this.tipoParticipante = tipoParticipante;
    }

    /**
     * Obtiene la dependencia del participante.
     * 
     * @return La dependencia del participante.
     */
    public String getDependencia() {
        return dependencia;
    }

    /**
     * Establece la dependencia del participante.
     * 
     * @param dependencia La dependencia del participante.
     */
    public void setDependencia(String dependencia) {
        this.dependencia = dependencia;
    }

    /**
     * Obtiene el número de control del participante.
     * 
     * @return El número de control del participante.
     */
    public String getNumeroControl() {
        return numeroControl;
    }

    /**
     * Establece el número de control del participante.
     * 
     * @param numeroControl El número de control del participante.
     */
    public void setNumeroControl(String numeroControl) {
        this.numeroControl = numeroControl;
    }

    /**
     * Obtiene la lista de actividades en las que participa el participante.
     * 
     * @return La lista de actividades del participante.
     */
    public List<Actividad> getActividades() {
        return actividades;
    }

    /**
     * Establece la lista de actividades en las que participa el participante.
     * 
     * @param actividades La lista de actividades del participante.
     */
    public void setActividades(List<Actividad> actividades) {
        this.actividades = actividades;
    }

    /**
     * Devuelve una representación en forma de cadena del participante.
     * 
     * @return Una cadena con los datos del participante.
     */
    @Override
    public String toString() {
        return "Participante [id=" + id + ", nombre=" + nombre + ", apellidoPaterno=" + apellidoPaterno
                + ", apellidoMaterno=" + apellidoMaterno + ", correo=" + correo + ", telefono=" + telefono
                + ", tipoParticipante=" + tipoParticipante + ", dependencia=" + dependencia + ", numeroControl="
                + numeroControl + "]";
    }
}