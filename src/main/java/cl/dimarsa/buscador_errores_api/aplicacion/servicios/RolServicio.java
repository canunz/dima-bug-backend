package cl.dimarsa.buscador_errores_api.aplicacion.servicios;

import cl.dimarsa.buscador_errores_api.dominio.modelo.Rol;
import cl.dimarsa.buscador_errores_api.dominio.puertos.entrada.RolCasoUso;
import cl.dimarsa.buscador_errores_api.dominio.puertos.salida.RolRepositorioPuerto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RolServicio implements RolCasoUso {

    private final RolRepositorioPuerto rolRepositorioPuerto;

    public RolServicio(RolRepositorioPuerto rolRepositorioPuerto) {
        this.rolRepositorioPuerto = rolRepositorioPuerto;
    }

    @Override
    public List<Rol> listarRoles() {
        return rolRepositorioPuerto.buscarTodos();
    }

    @Override
    public Rol obtenerRolPorId(Integer id) {
        return rolRepositorioPuerto.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Rol no encontrado con id: " + id));
    }

    @Override
    public Rol crearRol(Rol rol) {
        return rolRepositorioPuerto.guardar(rol);
    }
}