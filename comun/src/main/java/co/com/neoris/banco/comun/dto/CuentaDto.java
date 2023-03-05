package co.com.neoris.banco.comun.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CuentaDto {

	private Integer id;
	
	private String numeroCuenta;
	
	private String tipoCuenta;
	
	private Integer saldoInicial;
	
	private Boolean estado;
	
	private ClienteDto cliente;
	
}
