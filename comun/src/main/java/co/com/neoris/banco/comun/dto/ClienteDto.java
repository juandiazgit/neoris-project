package co.com.neoris.banco.comun.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClienteDto {

	private Integer id;
	
	private String clienteIden;
	
	private String contrasena;
	
	private Boolean estado;
	
	private PersonaDto persona;
	
}
