package cl.dimarsa.buscador_errores_api.aplicacion.servicios;

import cl.dimarsa.buscador_errores_api.dominio.modelo.Usuario;
import cl.dimarsa.buscador_errores_api.dominio.puertos.entrada.UsuarioCasoUso;
import cl.dimarsa.buscador_errores_api.dominio.puertos.salida.UsuarioRepositorioPuerto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioServicio implements UsuarioCasoUso {

    private final UsuarioRepositorioPuerto usuarioRepositorioPuerto;

    public UsuarioServicio(UsuarioRepositorioPuerto usuarioRepositorioPuerto) {
        this.usuarioRepositorioPuerto = usuarioRepositorioPuerto;
    }

    @Override
    public List<Usuario> listarUsuarios() {
        return usuarioRepositorioPuerto.buscarTodos();
    }

    @Override
    public Usuario obtenerUsuarioPorId(Integer id) {
        return usuarioRepositorioPuerto.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con id: " + id));
    }

    @Override
    public Usuario crearUsuario(Usuario usuario) {
        return usuarioRepositorioPuerto.guardar(usuario);
    }
}