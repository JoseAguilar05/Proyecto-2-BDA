package itson.dtos;

public class LugarDTO {
    private Integer id;
    private String nombre;
    private String edificio;

    /**
     * Constructor de la clase LugarDTO.
     * @param id El identificador único del lugar.
     * @param nombre El nombre del lugar.
     * @param edificio La dirección del edificio donde se encuentra el lugar.
     */
    public LugarDTO(Integer id, String nombre, String edificio) {
        this.id = id;
        this.nombre = nombre;
        this.edificio = edificio;
    }

    public Integer getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEdificio() {
        return edificio;
    }
}