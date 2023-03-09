package co.com.neoris.banco.gestorcliente.service;

import org.springframework.http.ResponseEntity;

import co.com.neoris.banco.comun.dto.ClienteDto;
import co.com.neoris.banco.comun.dto.ResponseDto;

public interface IClienteService {
	
	ResponseEntity<ResponseDto> createCliente(ClienteDto clienteDto);
	
	ResponseEntity<ResponseDto> getCliente(Integer identificacion);
	
	ResponseEntity<ResponseDto> updateCliente(Integer identificacion, ClienteDto clienteDto);
	
	ResponseEntity<ResponseDto> deleteCliente(Integer identificacion);
	
	ResponseEntity<ResponseDto> getAllCliente();
}
