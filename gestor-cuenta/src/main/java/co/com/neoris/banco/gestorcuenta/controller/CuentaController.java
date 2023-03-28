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

import co.com.neoris.banco.comun.dto.CuentaDto;
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
	public ResponseEntity<ResponseDto> getAllCliente(){
		return iCuentaService.getAllCuenta();
	}
	
	@GetMapping("cuenta/num-ident/{identificacion}")
	public ResponseEntity<ResponseDto> getClienteByIdentificacion(@PathVariable("identificacion") Integer identificacion){
		return iCuentaService.getCuenta(identificacion);
	}
	
	@PostMapping("cuenta")
	public ResponseEntity<ResponseDto> saveClienteByIdentificacion(@RequestBody CuentaGeneralDto cuentaGeneralDto){
		return iCuentaService.createCuenta(cuentaGeneralDto);
	}
	
	@DeleteMapping("cuenta/num-ident/{identificacion}")
	public ResponseEntity<ResponseDto> deleteClienteByIdentificacion(@PathVariable("identificacion") Integer identificacion){
		return iCuentaService.deleteCuenta(identificacion);
	}
	
	@PutMapping("cuenta/num-ident/{identificacion}")
	public ResponseEntity<ResponseDto> updateClienteByIdentificacion(@PathVariable("identificacion") Integer identificacion,
																	 @RequestBody CuentaDto cuentaDto){
		return iCuentaService.updateCuenta(identificacion,cuentaDto);
	}
}
