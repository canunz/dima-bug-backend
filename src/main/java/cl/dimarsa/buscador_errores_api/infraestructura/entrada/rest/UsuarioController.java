package cl.dimarsa.buscador_errores_api.infraestructura.entrada.rest;

import cl.dimarsa.buscador_errores_api.dominio.modelo.Usuario;
import cl.dimarsa.buscador_errores_api.dominio.puertos.entrada.UsuarioCasoUso;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioCasoUso usuarioCasoUso;

    public UsuarioController(UsuarioCasoUso usuarioCasoUso) {
        this.usuarioCasoUso = usuarioCasoUso;
    }

    @GetMapping
    public List<Usuario> listarUsuarios() {
        return usuarioCasoUso.listarUsuarios();
    }

    @GetMapping("/{id}")
    public Usuario obtenerUsuarioPorId(@PathVariable Integer id) {
        return usuarioCasoUso.obtenerUsuarioPorId(id);
    }

    @PostMapping
    public Usuario crearUsuario(@RequestBody Usuario usuario) {
        return usuarioCasoUso.crearUsuario(usuario);
    }
}