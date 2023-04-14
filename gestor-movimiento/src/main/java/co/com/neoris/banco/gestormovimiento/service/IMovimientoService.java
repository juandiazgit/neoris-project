package co.com.neoris.banco.gestormovimiento.service;

import org.springframework.http.ResponseEntity;

import co.com.neoris.banco.comun.dto.ResponseDto;
import co.com.neoris.banco.comun.dto.general.MovimientoGeneralDto;

public interface IMovimientoService {
	
	ResponseEntity<ResponseDto> createMovimiento(MovimientoGeneralDto movimientoGeneralDto);
	
	ResponseEntity<ResponseDto> getMovimiento(String numCuenta);
	
	ResponseEntity<ResponseDto> updateMovimiento(String numCuenta, Integer idMovimiento, MovimientoGeneralDto movimientoGeneralDto);
	
	ResponseEntity<ResponseDto> deleteMovimiento(String numCuenta, Integer idMovimiento);
}
