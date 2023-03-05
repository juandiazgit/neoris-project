package co.com.neoris.banco.gestorcliente.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.com.neoris.banco.comun.dto.ResponseDto;
import co.com.neoris.banco.gestorcliente.service.IClienteService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/")
public class ClienteController {
	
	private final IClienteService iClienteService;
	
	@GetMapping("cliente/num-ident/{identificacion}")
	public ResponseEntity<ResponseDto> getClienteByIdentificacion(@PathVariable("identificacion") Integer identificacion){
		return iClienteService.getCliente(identificacion);
	}
	
}
