package cl.dimarsa.buscador_errores_api.infraestructura.salida.persistencia;

import jakarta.persistence.*;

@Entity
@Table(name = "u_rol")
public class RolEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rol_id")
    private Integer id;

    @Column(name = "rol_nombre", nullable = false, unique = true, length = 50)
    private String nombre;

    @Column(name = "rol_descripcion", length = 200)
    private String descripcion;

    public RolEntity() {
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}