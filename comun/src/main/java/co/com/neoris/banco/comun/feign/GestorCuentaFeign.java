package co.com.neoris.banco.comun.feign;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "gestor-cuenta-service")
public interface GestorCuentaFeign {
	
}
