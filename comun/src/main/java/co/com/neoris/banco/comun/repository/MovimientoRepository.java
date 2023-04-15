package co.com.neoris.banco.comun.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.com.neoris.banco.comun.entity.MovimientoEntity;

@Repository
public interface MovimientoRepository extends JpaRepository<MovimientoEntity, Integer>{
	
	Optional<List<MovimientoEntity>> findByCuentaEntity_numeroCuenta(String numCuenta);
	
	Optional<MovimientoEntity> findByIdAndCuentaEntity_numeroCuenta(Integer idMovimiento,String numCuenta);
}
