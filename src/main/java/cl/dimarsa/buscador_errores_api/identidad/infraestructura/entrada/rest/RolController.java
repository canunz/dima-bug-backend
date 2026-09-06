package cl.dimarsa.buscador_errores_api.infraestructura.entrada.rest;

import cl.dimarsa.buscador_errores_api.dominio.modelo.Rol;
import cl.dimarsa.buscador_errores_api.dominio.puertos.entrada.RolCasoUso;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
public class RolController {

    private final RolCasoUso rolCasoUso;

    public RolController(RolCasoUso rolCasoUso) {
        this.rolCasoUso = rolCasoUso;
    }

    @GetMapping
    public List<Rol> listarRoles() {
        return rolCasoUso.listarRoles();
    }

    @GetMapping("/{id}")
    public Rol obtenerRolPorId(@PathVariable Integer id) {
        return rolCasoUso.obtenerRolPorId(id);
    }

    @PostMapping
    public Rol crearRol(@RequestBody Rol rol) {
        return rolCasoUso.crearRol(rol);
    }
}