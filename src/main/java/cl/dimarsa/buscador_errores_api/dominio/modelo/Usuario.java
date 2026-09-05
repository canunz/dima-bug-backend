package cl.dimarsa.buscador_errores_api.dominio.modelo;

import java.time.LocalDateTime;

public class Usuario {

    private Integer id;
    private Rol rol;
    private String nombre;
    private String email;
    private String passwordHash;
    private Boolean estado;
    private LocalDateTime fechaCreacion;

    public Usuario() {
    }

    public Usuario(Integer id, Rol rol, String nombre, String email, String passwordHash, Boolean estado, LocalDateTime fechaCreacion) {
        this.id = id;
        this.rol = rol;
        this.nombre = nombre;
        this.email = email;
        this.passwordHash = passwordHash;
        this.estado = estado;
        this.fechaCreacion = fechaCreacion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
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