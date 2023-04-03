package co.com.neoris.banco.gestorcuenta.service.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import co.com.neoris.banco.comun.dto.ClienteDto;
import co.com.neoris.banco.comun.dto.CuentaDto;
import co.com.neoris.banco.comun.dto.ResponseDto;
import co.com.neoris.banco.comun.dto.general.CuentaGeneralDto;
import co.com.neoris.banco.comun.entity.ClienteEntity;
import co.com.neoris.banco.comun.entity.CuentaEntity;
import co.com.neoris.banco.comun.exception.CustomException;
import co.com.neoris.banco.comun.feign.GestorClienteFeign;
import co.com.neoris.banco.comun.mapper.CuentaMapper;
import co.com.neoris.banco.comun.mapper.general.CuentaGeneralMapper;
import co.com.neoris.banco.comun.repository.CuentaRepository;
import co.com.neoris.banco.comun.util.Constants;
import co.com.neoris.banco.gestorcuenta.service.ICuentaService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@Slf4j
@Service
public class CuentaService implements ICuentaService{

	private final CuentaRepository cuentaRepository;
	private final GestorClienteFeign gestorClienteFeign;
	
	/**
	 * Crear cuenta
	 */
	@Override
	public ResponseEntity<ResponseDto> createCuenta(CuentaGeneralDto cuentaGeneralDto) {
		try {
			ClienteEntity clienteEntity = ClienteEntity.builder().build(); 
			ResponseEntity<ResponseDto> responseGestorCliente= gestorClienteFeign.getClienteByIdentificacion(cuentaGeneralDto.getNumeroIdentificacion());
			if (Objects.nonNull(responseGestorCliente.getBody()) && responseGestorCliente.getStatusCode().equals(HttpStatus.OK)) {
				Map<?, ?> linkedHashMap = (Map<?, ?>) responseGestorCliente.getBody().getObjectResponse();
				Integer clienteId = (Integer)linkedHashMap.get("id");
				if (Objects.nonNull(clienteId)) {
					clienteEntity =ClienteEntity.builder().id(clienteId).build();  
				}
			} else {
				log.error(Constants.ERROR_GET_CLIENTE,responseGestorCliente.getBody());
				throw new CustomException(Constants.ERROR_GET_CLIENTE);
			}
			CuentaDto cuentaDto = CuentaGeneralMapper.INSTANCE.genDtoToDto(cuentaGeneralDto);
			CuentaEntity cuentaEntity = CuentaMapper.INSTANCE.dtoToEntity(cuentaDto);
			cuentaEntity.setId(null);
			cuentaEntity.setClienteEntity(clienteEntity);
			cuentaEntity = cuentaRepository.save(cuentaEntity);
			CuentaDto cuentaDtoSave =  CuentaMapper.INSTANCE.entityToDto(cuentaEntity);
			CuentaGeneralDto cuentaGeneralDtoSave = CuentaGeneralMapper.INSTANCE.dtoToGenDto(cuentaDtoSave);
			cuentaGeneralDtoSave.setNumeroIdentificacion(cuentaGeneralDto.getNumeroIdentificacion());
			ResponseDto responseDto = ResponseDto.builder().menssage(HttpStatus.OK.name())
	                 				  .codeResponse(HttpStatus.OK.value()).objectResponse(cuentaGeneralDtoSave).build();
			return new ResponseEntity<>(responseDto,HttpStatus.OK);
		} catch(Exception ex) {
			log.error(Constants.ERROR_SAVE_CUENTA,ex);
			throw new CustomException(Constants.ERROR_SAVE_CUENTA+ex.getMessage());
		}
	}

	/**
	 * Consultar cuenta dado el numero de identificacion
	 */
	@Override
	public ResponseEntity<ResponseDto> getCuenta(Integer identificacion) {
		try {
			Optional<List<CuentaEntity>> listCuenta = cuentaRepository.findByClienteEntity_PersonaEntity_identificacion(identificacion);
			if(listCuenta.isPresent()) {
				List<CuentaDto> listCuentaDto = new ArrayList<>();
				List<CuentaGeneralDto> listCuentaGeneralDto = new ArrayList<>();
				
				listCuenta.get().stream().forEach(cuentaEnt -> listCuentaDto.add(CuentaMapper.INSTANCE.entityToDto(cuentaEnt)));
				listCuentaDto.stream().forEach(cuentaDto -> listCuentaGeneralDto.add(CuentaGeneralMapper.INSTANCE.dtoToGenDto(cuentaDto)));
				
				ResponseDto responseDto = ResponseDto.builder().menssage(HttpStatus.OK.name())
						                 .codeResponse(HttpStatus.OK.value()).objectResponse(listCuentaGeneralDto).build();
				return new ResponseEntity<>(responseDto,HttpStatus.OK);
			}
			return new ResponseEntity<>(ResponseDto.builder().menssage(Constants.NO_DATA_EXISTS)
	                	.codeResponse(HttpStatus.NOT_FOUND.value()).objectResponse(null).build(),HttpStatus.NOT_FOUND);
		}catch(Exception ex) {
			log.error(Constants.ERROR_GET_CUENTA,ex);
			throw new CustomException(Constants.ERROR_GET_CUENTA+ex.getMessage());
		}
	}

	/**
	 * Actualizar un cuenta
	 */
	@Override
	public ResponseEntity<ResponseDto> updateCuenta(Integer identificacion, CuentaDto cuentaDto) {
//		try {
//			Optional<ClienteEntity> clienteEnt = clienteRepository.findByPersonaEntity_identificacion(identificacion);
//			ResponseDto responseDto = null;
//			if (clienteEnt.isPresent()) {
//				ClienteEntity clienteSent = ClienteMapper.INSTANCE.dtoToEntity(clienteDto);
//				clienteSent.setId(clienteEnt.get().getId());
//				clienteSent.getPersonaEntity().setId(clienteEnt.get().getPersonaEntity().getId());
//				
//				clienteEnt = Optional.of(clienteRepository.save(clienteSent));
//				ClienteDto clienteDtoUpd =  ClienteMapper.INSTANCE.entityToDto(clienteEnt.get());
//				responseDto = ResponseDto.builder().menssage(HttpStatus.OK.name())
//		   				  .codeResponse(HttpStatus.OK.value()).objectResponse(clienteDtoUpd).build();
//			} else {
//				log.error(Constants.NO_DATA_EXISTS);
//				throw new CustomException(Constants.NO_DATA_EXISTS);
//			}
//			return new ResponseEntity<>(responseDto,HttpStatus.OK);
//		} catch(Exception ex) {
//			log.error(Constants.ERROR_UPDATE_CLIENTE,ex);
//			throw new CustomException(Constants.ERROR_UPDATE_CLIENTE+ex.getMessage());
//		}
		return null; //Borrar
	}

	/**
	 * Eliminar un cuenta
	 */
	@Override
	public ResponseEntity<ResponseDto> deleteCuenta(Integer identificacion) {
//		try {
//			Optional<ClienteEntity> cliente = clienteRepository.findByPersonaEntity_identificacion(identificacion);
//			if (cliente.isPresent()) {
//				clienteRepository.delete(cliente.get());
//				ResponseDto responseDto = ResponseDto.builder().menssage(Constants.DELETE_CLIENTE)
//						                 .codeResponse(HttpStatus.OK.value()).objectResponse(null).build();
//				return new ResponseEntity<>(responseDto,HttpStatus.OK);
//			} else {
//				log.error(Constants.NO_DATA_EXISTS);
//				throw new CustomException(Constants.NO_DATA_EXISTS);
//			}
//		} catch (Exception ex) {
//			log.error(Constants.ERROR_DELETE_CLIENTE,ex);
//			throw new CustomException(Constants.ERROR_DELETE_CLIENTE+ex.getMessage());
//		}
		return null; //Borrar
	}

	/**
	 * Consultar la lista de cuentas
	 */
	@Override
	public ResponseEntity<ResponseDto> getAllCuenta() {
//		try {
//			List<ClienteEntity> listClienteEnt = clienteRepository.findAll();
//			List<ClienteDto> listClienteDto = new ArrayList<>();
//			if(!listClienteEnt.isEmpty()) {
//				listClienteEnt.stream().forEach((clienteEnt)->{
//					listClienteDto.add(ClienteMapper.INSTANCE.entityToDto(clienteEnt));
//				});
//				ResponseDto responseDto = ResponseDto.builder().menssage(HttpStatus.OK.name())
//						                 .codeResponse(HttpStatus.OK.value()).objectResponse(listClienteDto).build();
//				return new ResponseEntity<>(responseDto,HttpStatus.OK);
//			}
//			return new ResponseEntity<>(ResponseDto.builder().menssage(Constants.NO_DATA_EXISTS)
//	                	.codeResponse(HttpStatus.NOT_FOUND.value()).objectResponse(null).build(),HttpStatus.NOT_FOUND);
//		}catch(Exception ex) {
//			log.error(Constants.ERROR_GET_ALL_CLIENTE,ex);
//			throw new CustomException(Constants.ERROR_GET_ALL_CLIENTE+ex.getMessage());
//		}
		return null; //Borrar
	}

}
