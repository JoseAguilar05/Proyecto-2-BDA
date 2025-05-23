package itson.dtos;

import java.util.List;
import itson.enums.TipoParticipante;

public class ParticipanteDTO {
    private Integer id;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String correo;
    private TipoParticipante tipoParticipante;
    private String dependencia;
    private String numeroControl;
    private List<Integer> actividadesIds;

    /**
     * Constructor de la clase ParticipanteDTO.
     * @param id El identificador único del participante.
     * @param nombre El nombre del participante.
     * @param apellidoPaterno El apellido paterno del participante.
     * @param apellidoMaterno El apellido materno del participante.
     * @param correo El correo electrónico del participante.
     * @param tipoParticipante El tipo de participante (Ejecutivo, Coordinador, etc.).
     * @param dependencia La dependencia a la que pertenece el participante.
     * @param numeroControl El número de control del participante (si aplica).
     */
    public ParticipanteDTO(Integer id, String nombre, String apellidoPaterno, String apellidoMaterno, String correo,
     TipoParticipante tipoParticipante, String dependencia, String numeroControl) {
        this.id = id;
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.correo = correo;
        this.tipoParticipante = tipoParticipante;
        this.dependencia = dependencia;
        this.numeroControl = numeroControl;
    }

    /**
     * Constructor de la clase ParticipanteDTO sin Id.
     * @param nombre El nombre del participante.
     * @param apellidoPaterno El apellido paterno del participante.
     * @param apellidoMaterno El apellido materno del participante.
     * @param correo El correo electrónico del participante.
     * @param tipoParticipante El tipo de participante (Ejecutivo, Coordinador, etc.).
     * @param dependencia La dependencia a la que pertenece el participante.
     * @param numeroControl El número de control del participante (si aplica).
     */
    public ParticipanteDTO( String nombre, String apellidoPaterno, String apellidoMaterno, String correo,
            TipoParticipante tipoParticipante, String dependencia, String numeroControl){ 
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.correo = correo;
        this.tipoParticipante = tipoParticipante;
        this.dependencia = dependencia;
        this.numeroControl = numeroControl;
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

    public TipoParticipante getTipoParticipante() {
        return tipoParticipante;
    }

    public String getDependencia() {
        return dependencia;
    }

    public String getNumeroControl() {
        return numeroControl;
    }

    public List<Integer> getActividadesIds() {
        return actividadesIds;
    }
}