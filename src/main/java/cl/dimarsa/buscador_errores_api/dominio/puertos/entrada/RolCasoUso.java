package cl.dimarsa.buscador_errores_api.dominio.puertos.entrada;

import cl.dimarsa.buscador_errores_api.dominio.modelo.Rol;

import java.util.List;

public interface RolCasoUso {

    List<Rol> listarRoles();

    Rol obtenerRolPorId(Integer id);

    Rol crearRol(Rol rol);
}