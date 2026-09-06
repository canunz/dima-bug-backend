package cl.dimarsa.buscador_errores_api.dominio.puertos.entrada;

import cl.dimarsa.buscador_errores_api.dominio.modelo.Usuario;

import java.util.List;

public interface UsuarioCasoUso {

    List<Usuario> listarUsuarios();

    Usuario obtenerUsuarioPorId(Integer id);

    Usuario crearUsuario(Usuario usuario);
}