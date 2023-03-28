package co.com.neoris.banco.comun.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import co.com.neoris.banco.comun.dto.ResponseDto;

@FeignClient(name = "gestor-cliente-service")
public interface GestorClienteFeign {
	
	@GetMapping("api/v1/cliente/num-ident/{identificacion}")
	public ResponseEntity<ResponseDto> getClienteByIdentificacion(@PathVariable("identificacion") Integer identificacion);
}
