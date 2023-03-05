package co.com.neoris.banco.comun.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.com.neoris.banco.comun.entity.ClienteEntity;

@Repository
public interface ClienteRepository extends JpaRepository<ClienteEntity, Integer>{
	
	Optional<ClienteEntity> findByPersonaEntity_identificacion(Integer identificacion);
	
}
