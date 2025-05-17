package itson.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "lugares")
public class Lugar {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nombre", length = 50, nullable = false)
    private String nombre;

    @Column(name = "edificio", length = 100, nullable = false)
    private String edificio;

    public Lugar() {}

    public Lugar(Integer id, String nombre, String edificio) {
        this.id = id;
        this.nombre = nombre;
        this.edificio = edificio;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEdificio() {
        return edificio;
    }

    public void setEdificio(String edificio) {
        this.edificio = edificio;
    }

    @Override
    public String toString() {
        return "Lugar{" +
                "id='" + id + '\'' +
                ", nombre='" + nombre + '\'' +
                ", edificio='" + edificio + '\'' +
                '}';
    }

}
