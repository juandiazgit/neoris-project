package co.com.neoris.banco.gestormovimiento.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.com.neoris.banco.comun.dto.ResponseDto;
import co.com.neoris.banco.comun.dto.general.MovimientoGeneralDto;
import co.com.neoris.banco.gestormovimiento.service.IMovimientoService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/")
public class MovimientoController {
	
	private final IMovimientoService iMovimientoService;
	
	@GetMapping("movimiento/num-cuenta/{numCuenta}")
	public ResponseEntity<ResponseDto> getMovimiento(@PathVariable("numCuenta") String numCuenta){
		return iMovimientoService.getMovimiento(numCuenta);
	}
	
	@PostMapping("movimiento")
	public ResponseEntity<ResponseDto> saveMovimiento(@RequestBody MovimientoGeneralDto movimientoGeneralDto){
		return iMovimientoService.createMovimiento(movimientoGeneralDto);
	}
	
	@PutMapping("movimiento/num-cuenta/{numCuenta}/id-movimiento/{idMovimiento}")
	public ResponseEntity<ResponseDto> updateMovimiento(@PathVariable("numCuenta") String numCuenta,
														@PathVariable("idMovimiento") Integer idMovimiento,
														@RequestBody MovimientoGeneralDto movimientoGeneralDto){
		return iMovimientoService.updateMovimiento(numCuenta,idMovimiento,movimientoGeneralDto);
	}
	
	@DeleteMapping("movimiento/num-cuenta/{numCuenta}/id-movimiento/{idMovimiento}")
	public ResponseEntity<ResponseDto> deleteMovimiento(@PathVariable("numCuenta") String numCuenta,
														@PathVariable("idMovimiento") Integer idMovimiento){
		return iMovimientoService.deleteMovimiento(numCuenta,idMovimiento);
	}
}
