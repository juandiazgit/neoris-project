package co.com.neoris.banco.gestorcliente.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.com.neoris.banco.comun.dto.ClienteDto;
import co.com.neoris.banco.comun.dto.ResponseDto;
import co.com.neoris.banco.gestorcliente.service.IClienteService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/")
public class ClienteController {
	
	private final IClienteService iClienteService;

	@GetMapping("cliente")
	public ResponseEntity<ResponseDto> getAllCliente(){
		return iClienteService.getAllCliente();
	}
	
	@GetMapping("cliente/num-ident/{identificacion}")
	public ResponseEntity<ResponseDto> getClienteByIdentificacion(@PathVariable("identificacion") Integer identificacion){
		return iClienteService.getCliente(identificacion);
	}
	
	@PostMapping("cliente")
	public ResponseEntity<ResponseDto> saveClienteByIdentificacion(@RequestBody ClienteDto clienteDto){
		return iClienteService.createCliente(clienteDto);
	}
	
	@DeleteMapping("cliente/num-ident/{identificacion}")
	public ResponseEntity<ResponseDto> deleteClienteByIdentificacion(@PathVariable("identificacion") Integer identificacion){
		return iClienteService.deleteCliente(identificacion);
	}
	
	@PutMapping("cliente/num-ident/{identificacion}")
	public ResponseEntity<ResponseDto> updateClienteByIdentificacion(@PathVariable("identificacion") Integer identificacion,
																	 @RequestBody ClienteDto clienteDto){
		return iClienteService.updateCliente(identificacion,clienteDto);
	}
	
}
