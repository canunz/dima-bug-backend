package cl.dimarsa.buscador_errores_api.dominio.puertos.salida;

import cl.dimarsa.buscador_errores_api.dominio.modelo.Rol;

import java.util.List;
import java.util.Optional;

public interface RolRepositorioPuerto {

    List<Rol> buscarTodos();

    Optional<Rol> buscarPorId(Integer id);

    Rol guardar(Rol rol);
}