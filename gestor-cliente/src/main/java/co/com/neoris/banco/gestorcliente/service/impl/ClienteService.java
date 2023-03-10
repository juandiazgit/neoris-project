package co.com.neoris.banco.gestorcliente.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import co.com.neoris.banco.comun.dto.ClienteDto;
import co.com.neoris.banco.comun.dto.ResponseDto;
import co.com.neoris.banco.comun.entity.ClienteEntity;
import co.com.neoris.banco.comun.exception.CustomException;
import co.com.neoris.banco.comun.mapper.ClienteMapper;
import co.com.neoris.banco.comun.mapper.PersonaMapper;
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
		try {
			ClienteEntity clienteEntity = ClienteMapper.INSTANCE.dtoToEntity(clienteDto);
			clienteEntity.setId(null);
			clienteEntity.getPersonaEntity().setId(null);
			clienteEntity = clienteRepository.save(clienteEntity);
			ClienteDto clienteDtoSave =  ClienteMapper.INSTANCE.entityToDto(clienteEntity);
			ResponseDto responseDto = ResponseDto.builder().menssage(HttpStatus.OK.name())
	                 				  .codeResponse(HttpStatus.OK.value()).objectResponse(clienteDtoSave).build();
			return new ResponseEntity<>(responseDto,HttpStatus.OK);
		} catch(Exception ex) {
			log.error(Constants.ERROR_SAVE_CLIENTE,ex);
			throw new CustomException(Constants.ERROR_SAVE_CLIENTE+ex.getMessage());
		}
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

	/**
	 * Actualizar un cliente
	 */
	@Override
	public ResponseEntity<ResponseDto> updateCliente(Integer identificacion, ClienteDto clienteDto) {
		try {
			Optional<ClienteEntity> clienteEnt = clienteRepository.findByPersonaEntity_identificacion(identificacion);
			ResponseDto responseDto = null;
			if (clienteEnt.isPresent()) {
				ClienteEntity clienteSent = ClienteMapper.INSTANCE.dtoToEntity(clienteDto);
				clienteSent.setId(clienteEnt.get().getId());
				clienteSent.getPersonaEntity().setId(clienteEnt.get().getPersonaEntity().getId());
				
				clienteEnt = Optional.of(clienteRepository.save(clienteSent));
				ClienteDto clienteDtoUpd =  ClienteMapper.INSTANCE.entityToDto(clienteEnt.get());
				responseDto = ResponseDto.builder().menssage(HttpStatus.OK.name())
		   				  .codeResponse(HttpStatus.OK.value()).objectResponse(clienteDtoUpd).build();
			} else {
				log.error(Constants.NO_DATA_EXISTS);
				throw new CustomException(Constants.NO_DATA_EXISTS);
			}
			return new ResponseEntity<>(responseDto,HttpStatus.OK);
		} catch(Exception ex) {
			log.error(Constants.ERROR_UPDATE_CLIENTE,ex);
			throw new CustomException(Constants.ERROR_UPDATE_CLIENTE+ex.getMessage());
		}
	}

	/**
	 * Eliminar un cliente
	 */
	@Override
	public ResponseEntity<ResponseDto> deleteCliente(Integer identificacion) {
		try {
			Optional<ClienteEntity> cliente = clienteRepository.findByPersonaEntity_identificacion(identificacion);
			if (cliente.isPresent()) {
				clienteRepository.delete(cliente.get());
				ResponseDto responseDto = ResponseDto.builder().menssage(Constants.DELETE_CLIENTE)
						                 .codeResponse(HttpStatus.OK.value()).objectResponse(null).build();
				return new ResponseEntity<>(responseDto,HttpStatus.OK);
			} else {
				log.error(Constants.NO_DATA_EXISTS);
				throw new CustomException(Constants.NO_DATA_EXISTS);
			}
		} catch (Exception ex) {
			log.error(Constants.ERROR_DELETE_CLIENTE,ex);
			throw new CustomException(Constants.ERROR_DELETE_CLIENTE+ex.getMessage());
		}
	}

	/**
	 * Consultar la lista de clientes
	 */
	@Override
	public ResponseEntity<ResponseDto> getAllCliente() {
		try {
			List<ClienteEntity> listClienteEnt = clienteRepository.findAll();
			List<ClienteDto> listClienteDto = new ArrayList<>();
			if(!listClienteEnt.isEmpty()) {
				listClienteEnt.stream().forEach((clienteEnt)->{
					listClienteDto.add(ClienteMapper.INSTANCE.entityToDto(clienteEnt));
				});
				ResponseDto responseDto = ResponseDto.builder().menssage(HttpStatus.OK.name())
						                 .codeResponse(HttpStatus.OK.value()).objectResponse(listClienteDto).build();
				return new ResponseEntity<>(responseDto,HttpStatus.OK);
			}
			return new ResponseEntity<>(ResponseDto.builder().menssage(Constants.NO_DATA_EXISTS)
	                	.codeResponse(HttpStatus.NOT_FOUND.value()).objectResponse(null).build(),HttpStatus.NOT_FOUND);
		}catch(Exception ex) {
			log.error(Constants.ERROR_GET_ALL_CLIENTE,ex);
			throw new CustomException(Constants.ERROR_GET_ALL_CLIENTE+ex.getMessage());
		}
	}

}
