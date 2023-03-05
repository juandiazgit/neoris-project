package co.com.neoris.banco.comun.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDto {
	
	private Integer codeResponse;
	private String menssage;
	private Object objectResponse;
	
}
