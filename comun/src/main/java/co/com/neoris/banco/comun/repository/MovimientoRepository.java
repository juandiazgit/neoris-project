package co.com.neoris.banco.comun.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.com.neoris.banco.comun.entity.MovimientoEntity;

@Repository
public interface MovimientoRepository extends JpaRepository<MovimientoEntity, Integer>{
	
}
