package co.com.neoris.banco.gestorcuenta.service;

import org.springframework.http.ResponseEntity;

import co.com.neoris.banco.comun.dto.CuentaDto;
import co.com.neoris.banco.comun.dto.ResponseDto;

public interface ICuentaService {
	
	ResponseEntity<ResponseDto> createCuenta(CuentaDto cuentaDto);
	
	ResponseEntity<ResponseDto> getCuenta(Integer identificacion);
	
	ResponseEntity<ResponseDto> updateCuenta(Integer identificacion, CuentaDto cuentaDto);
	
	ResponseEntity<ResponseDto> deleteCuenta(Integer identificacion);
	
	ResponseEntity<ResponseDto> getAllCuenta();
}
