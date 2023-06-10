package co.com.neoris.banco.comun.dto.general;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MovimientoGeneralDto {

	private Integer id;

	private LocalDate fecha;
	
	private String tipoMovimiento;
	
	private Integer valor;

	private Integer saldo;
	
	private Boolean estado;
	
	private String numeroCuenta;
	
	private String tipoCuenta;
	
	private String nombreCliente;
}
