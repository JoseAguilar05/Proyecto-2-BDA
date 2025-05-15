package itson.dtos;

import java.util.List;
import itson.enums.TipoParticipante;

public class ParticipanteDTO {
    private Integer id;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String correo;
    private String telefono;
    private TipoParticipante tipoParticipante;
    private String dependencia;
    private String numeroControl;
    private List<String> actividadesIds;

    /**
     * Constructor de la clase ParticipanteDTO.
     * @param id El identificador único del participante.
     * @param nombre El nombre del participante.
     * @param apellidoPaterno El apellido paterno del participante.
     * @param apellidoMaterno El apellido materno del participante.
     * @param correo El correo electrónico del participante.
     * @param telefono El número de teléfono del participante.
     * @param tipoParticipante El tipo de participante (Ejecutivo, Coordinador, etc.).
     * @param dependencia La dependencia a la que pertenece el participante.
     * @param numeroControl El número de control del participante (si aplica).
     * @param actividadesIds Lista de identificadores de actividades en las que participa el participante.
     */
    public ParticipanteDTO(Integer id, String nombre, String apellidoPaterno, String apellidoMaterno, String correo,
            String telefono, TipoParticipante tipoParticipante, String dependencia, String numeroControl,
            List<String> actividadesIds) {
        this.id = id;
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.correo = correo;
        this.telefono = telefono;
        this.tipoParticipante = tipoParticipante;
        this.dependencia = dependencia;
        this.numeroControl = numeroControl;
        this.actividadesIds = actividadesIds;
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

    public String getCorreo() {
        return correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public TipoParticipante getTipoParticipante() {
        return tipoParticipante;
    }

    public String getDependencia() {
        return dependencia;
    }

    public String getNumeroControl() {
        return numeroControl;
    }

    public List<String> getActividadesIds() {
        return actividadesIds;
    }
}