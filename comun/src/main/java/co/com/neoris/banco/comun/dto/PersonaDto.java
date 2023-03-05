package co.com.neoris.banco.comun.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PersonaDto {

	private Integer id;
	
	private String nombre;
	
	private String genero;
	
	private Integer edad;
	
	private Integer identificacion;
	
	private String direccion;
	
	private String telefono;
	
}
