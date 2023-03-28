package co.com.neoris.banco.gestorcuenta.service;

import org.springframework.http.ResponseEntity;

import co.com.neoris.banco.comun.dto.CuentaDto;
import co.com.neoris.banco.comun.dto.ResponseDto;
import co.com.neoris.banco.comun.dto.general.CuentaGeneralDto;

public interface ICuentaService {
	
	ResponseEntity<ResponseDto> createCuenta(CuentaGeneralDto cuentaGeneralDto);
	
	ResponseEntity<ResponseDto> getCuenta(Integer identificacion);
	
	ResponseEntity<ResponseDto> updateCuenta(Integer identificacion, CuentaDto cuentaDto);
	
	ResponseEntity<ResponseDto> deleteCuenta(Integer identificacion);
	
	ResponseEntity<ResponseDto> getAllCuenta();
}
