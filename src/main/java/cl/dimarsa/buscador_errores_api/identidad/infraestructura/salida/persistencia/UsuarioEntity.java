package cl.dimarsa.buscador_errores_api.infraestructura.salida.persistencia;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "u_usuario")
public class UsuarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usuario_id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "rol_id", nullable = false)
    private RolEntity rol;

    @Column(name = "usuario_nombre", nullable = false, length = 100)
    private String nombre;

    @Column(name = "usuario_email", nullable = false, unique = true, length = 120)
    private String email;

    @Column(name = "usuario_password_hash", nullable = false, length = 255)
    private String passwordHash;

    @Column(name = "usuario_estado", nullable = false)
    private Boolean estado;

    @Column(name = "usuario_fecha_creacion", nullable = false)
    private LocalDateTime fechaCreacion;

    public UsuarioEntity() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public RolEntity getRol() {
        return rol;
    }

    public void setRol(RolEntity rol) {
        this.rol = rol;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
}