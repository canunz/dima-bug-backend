package cl.dimarsa.buscador_errores_api.infraestructura.salida.persistencia;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RolJpaRepository extends JpaRepository<RolEntity, Integer> {
}