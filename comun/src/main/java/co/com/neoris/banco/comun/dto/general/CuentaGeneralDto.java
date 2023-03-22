package co.com.neoris.banco.comun.dto.general;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CuentaGeneralDto {

	private Integer id;
	
	private String numeroCuenta;
	
	private String tipoCuenta;
	
	private Integer saldoInicial;
	
	private Boolean estado;
	
	private String numeroIdentificacion;
	
}
