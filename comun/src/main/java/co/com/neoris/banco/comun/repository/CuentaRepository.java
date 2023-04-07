package co.com.neoris.banco.comun.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.com.neoris.banco.comun.entity.CuentaEntity;

@Repository
public interface CuentaRepository extends JpaRepository<CuentaEntity, Integer>{
	
	Optional<List<CuentaEntity>> findByClienteEntity_PersonaEntity_identificacion(Integer identificacion);
	
	Optional<CuentaEntity> findByNumeroCuentaAndClienteEntity_PersonaEntity_identificacion(String numeroCuenta,
			                    														   Integer identificacion);
	
	Optional<CuentaEntity> findByNumeroCuenta(String numeroCuenta);
}
