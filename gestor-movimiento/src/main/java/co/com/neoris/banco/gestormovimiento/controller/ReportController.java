package co.com.neoris.banco.gestormovimiento.controller;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.com.neoris.banco.comun.dto.ResponseDto;
import co.com.neoris.banco.gestormovimiento.service.IMovimientoService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/")
public class ReportController {
	
	private final IMovimientoService iMovimientoService;
	
	@GetMapping("movimiento/reporte/num-identi/{numIdenti}")
	public ResponseEntity<ResponseDto> retrieveReportByDates(@PathVariable("numIdenti") Integer numIdentificacion,
															 @RequestParam("fechaInicio") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fechaInicio,
												             @RequestParam("fechaFin") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fechaFin){
		return iMovimientoService.retrieveReportByDates(numIdentificacion, fechaInicio, fechaFin);
	}
	
}
