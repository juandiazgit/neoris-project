package co.com.neoris.banco.gestorcuenta.controller;

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
import co.com.neoris.banco.comun.dto.general.CuentaGeneralDto;
import co.com.neoris.banco.gestorcuenta.service.ICuentaService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/")
public class CuentaController {
	
	private final ICuentaService iCuentaService;

	@GetMapping("cuenta")
	public ResponseEntity<ResponseDto> getAllCuenta(){
		return iCuentaService.getAllCuenta();
	}
	
	@GetMapping("cuenta/num-ident/{identificacion}")
	public ResponseEntity<ResponseDto> getCuenta(@PathVariable("identificacion") Integer identificacion){
		return iCuentaService.getCuenta(identificacion);
	}
	
	@PostMapping("cuenta")
	public ResponseEntity<ResponseDto> saveCuenta(@RequestBody CuentaGeneralDto cuentaGeneralDto){
		return iCuentaService.createCuenta(cuentaGeneralDto);
	}
	
	@DeleteMapping("cuenta/num-ident/{identificacion}/num-cuenta/{numCuenta}")
	public ResponseEntity<ResponseDto> deleteCuenta(@PathVariable("identificacion") Integer identificacion,
													@PathVariable("numCuenta") String numCuenta){
		return iCuentaService.deleteCuenta(identificacion,numCuenta);
	}
	
	@PutMapping("cuenta/num-ident/{identificacion}")
	public ResponseEntity<ResponseDto> updateCuenta(@PathVariable("identificacion") Integer identificacion,
																	@RequestBody CuentaGeneralDto cuentaGeneralDto){
		return iCuentaService.updateCuenta(identificacion,cuentaGeneralDto);
	}
}
