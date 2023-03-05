package co.com.neoris.banco.gestorcliente.service.impl;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import co.com.neoris.banco.comun.dto.ClienteDto;
import co.com.neoris.banco.comun.dto.ResponseDto;
import co.com.neoris.banco.comun.entity.ClienteEntity;
import co.com.neoris.banco.comun.exception.CustomException;
import co.com.neoris.banco.comun.mapper.ClienteMapper;
import co.com.neoris.banco.comun.repository.ClienteRepository;
import co.com.neoris.banco.comun.util.Constants;
import co.com.neoris.banco.gestorcliente.service.IClienteService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@Slf4j
@Service
public class ClienteService implements IClienteService{

	private final ClienteRepository clienteRepository;
	
	@Override
	public ResponseEntity<ResponseDto> createCliente(ClienteDto clienteDto) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Consultar cliente dado el numero de identificacion
	 */
	@Override
	public ResponseEntity<ResponseDto> getCliente(Integer identificacion) {
		try {
			Optional<ClienteEntity> cliente = clienteRepository.findByPersonaEntity_identificacion(identificacion);
			if(cliente.isPresent()) {
				ClienteDto clienteDto=ClienteMapper.INSTANCE.entityToDto(cliente.get());
				ResponseDto responseDto = ResponseDto.builder().menssage(HttpStatus.OK.name())
						                 .codeResponse(HttpStatus.OK.value()).objectResponse(clienteDto).build();
				return new ResponseEntity<>(responseDto,HttpStatus.OK);
			}
			return new ResponseEntity<>(ResponseDto.builder().menssage(Constants.NO_DATA_EXISTS)
	                	.codeResponse(HttpStatus.NOT_FOUND.value()).objectResponse(null).build(),HttpStatus.NOT_FOUND);
		}catch(Exception ex) {
			log.error(Constants.ERROR_GET_CLIENTE,ex);
			throw new CustomException(Constants.ERROR_GET_CLIENTE+ex.getMessage());
		}
	}

	@Override
	public ResponseEntity<ResponseDto> updateCliente(Integer identificacion) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<String> deleteCliente(Integer identificacion) {
		// TODO Auto-generated method stub
		return null;
	}

}
