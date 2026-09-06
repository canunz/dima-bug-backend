package cl.dimarsa.buscador_errores_api.infraestructura.salida.persistencia;

import cl.dimarsa.buscador_errores_api.dominio.modelo.Rol;
import cl.dimarsa.buscador_errores_api.dominio.puertos.salida.RolRepositorioPuerto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class RolRepositorioAdaptador implements RolRepositorioPuerto {

    private final RolJpaRepository rolJpaRepository;

    public RolRepositorioAdaptador(RolJpaRepository rolJpaRepository) {
        this.rolJpaRepository = rolJpaRepository;
    }

    @Override
    public List<Rol> buscarTodos() {
        return rolJpaRepository.findAll().stream()
                .map(this::convertirADominio)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Rol> buscarPorId(Integer id) {
        return rolJpaRepository.findById(id)
                .map(this::convertirADominio);
    }

    @Override
    public Rol guardar(Rol rol) {
        RolEntity entity = convertirAEntity(rol);
        RolEntity guardado = rolJpaRepository.save(entity);
        return convertirADominio(guardado);
    }

    private Rol convertirADominio(RolEntity entity) {
        return new Rol(entity.getId(), entity.getNombre(), entity.getDescripcion());
    }

    private RolEntity convertirAEntity(Rol rol) {
        RolEntity entity = new RolEntity();
        entity.setId(rol.getId());
        entity.setNombre(rol.getNombre());
        entity.setDescripcion(rol.getDescripcion());
        return entity;
    }
}