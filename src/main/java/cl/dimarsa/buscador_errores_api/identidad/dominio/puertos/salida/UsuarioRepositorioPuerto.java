package cl.dimarsa.buscador_errores_api.dominio.puertos.salida;

import cl.dimarsa.buscador_errores_api.dominio.modelo.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepositorioPuerto {

    List<Usuario> buscarTodos();

    Optional<Usuario> buscarPorId(Integer id);

    Optional<Usuario> buscarPorEmail(String email);

    Usuario guardar(Usuario usuario);
}
