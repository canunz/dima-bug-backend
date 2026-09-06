package cl.dimarsa.buscador_errores_api.infraestructura.salida.persistencia;

import cl.dimarsa.buscador_errores_api.dominio.modelo.Rol;
import cl.dimarsa.buscador_errores_api.dominio.modelo.Usuario;
import cl.dimarsa.buscador_errores_api.dominio.puertos.salida.UsuarioRepositorioPuerto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class UsuarioRepositorioAdaptador implements UsuarioRepositorioPuerto {

    private final UsuarioJpaRepository usuarioJpaRepository;

    public UsuarioRepositorioAdaptador(UsuarioJpaRepository usuarioJpaRepository) {
        this.usuarioJpaRepository = usuarioJpaRepository;
    }

    @Override
    public List<Usuario> buscarTodos() {
        return usuarioJpaRepository.findAll().stream()
                .map(this::convertirADominio)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Usuario> buscarPorId(Integer id) {
        return usuarioJpaRepository.findById(id)
                .map(this::convertirADominio);
    }

    @Override
    public Optional<Usuario> buscarPorEmail(String email) {
        return usuarioJpaRepository.findByEmail(email)
                .map(this::convertirADominio);
    }

    @Override
    public Usuario guardar(Usuario usuario) {
        UsuarioEntity entity = convertirAEntity(usuario);
        UsuarioEntity guardado = usuarioJpaRepository.save(entity);
        return convertirADominio(guardado);
    }

    private Usuario convertirADominio(UsuarioEntity entity) {
        Rol rol = new Rol(entity.getRol().getId(), entity.getRol().getNombre(), entity.getRol().getDescripcion());
        return new Usuario(entity.getId(), rol, entity.getNombre(), entity.getEmail(),
                entity.getPasswordHash(), entity.getEstado(), entity.getFechaCreacion());
    }

    private UsuarioEntity convertirAEntity(Usuario usuario) {
        UsuarioEntity entity = new UsuarioEntity();
        entity.setId(usuario.getId());
        RolEntity rolEntity = new RolEntity();
        rolEntity.setId(usuario.getRol().getId());
        entity.setRol(rolEntity);
        entity.setNombre(usuario.getNombre());
        entity.setEmail(usuario.getEmail());
        entity.setPasswordHash(usuario.getPasswordHash());
        entity.setEstado(usuario.getEstado());
        entity.setFechaCreacion(usuario.getFechaCreacion());
        return entity;
    }
}