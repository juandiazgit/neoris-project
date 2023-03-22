package co.com.neoris.banco.gestorcuenta.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import co.com.neoris.banco.comun.dto.ResponseDto;
import co.com.neoris.banco.comun.exception.CustomException;
import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class ApiExcepcionHandler {

	@ExceptionHandler(CustomException.class)
	public ResponseEntity<ResponseDto> customException(CustomException ex){
		log.error(ex.getMessage());
		ResponseDto responseDto = ResponseDto.builder().menssage(ex.getMessage())
				  .codeResponse(HttpStatus.CONFLICT.value()).objectResponse(null).build();
		return new ResponseEntity<>(responseDto,HttpStatus.CONFLICT);
	}
	
	@ExceptionHandler({RuntimeException.class,Exception.class})
	public ResponseEntity<ResponseDto> excepcionInternal(Exception ex){
		log.error(ex.getMessage());
		ResponseDto responseDto = ResponseDto.builder().menssage(ex.getMessage())
				  .codeResponse(HttpStatus.INTERNAL_SERVER_ERROR.value()).objectResponse(null).build();
		return new ResponseEntity<>(responseDto,HttpStatus.CONFLICT);
	}
	
}
