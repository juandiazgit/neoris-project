package co.com.neoris.banco.gestormovimiento.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import co.com.neoris.banco.comun.dto.MovimientoDto;
import co.com.neoris.banco.comun.dto.ResponseDto;
import co.com.neoris.banco.comun.dto.general.MovimientoGeneralDto;
import co.com.neoris.banco.comun.entity.CuentaEntity;
import co.com.neoris.banco.comun.entity.MovimientoEntity;
import co.com.neoris.banco.comun.exception.CustomException;
import co.com.neoris.banco.comun.mapper.MovimientoMapper;
import co.com.neoris.banco.comun.mapper.general.MovimientoGeneralMapper;
import co.com.neoris.banco.comun.repository.CuentaRepository;
import co.com.neoris.banco.comun.repository.MovimientoRepository;
import co.com.neoris.banco.comun.util.Constants;
import co.com.neoris.banco.gestormovimiento.service.IMovimientoService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@Slf4j
@Service
public class MovimientoService implements IMovimientoService{

	private final MovimientoRepository movimientoRepository;
	private final CuentaRepository cuentaRepository;
	
	private final ValidateMovimiento validateMovimiento;
	
	/**
	 * Crear movimiento
	 */
	@Override
	public ResponseEntity<ResponseDto> createMovimiento(MovimientoGeneralDto movimientoGeneralDto) {
		try {
			Optional<CuentaEntity> cuentaEntity = cuentaRepository.findByNumeroCuenta(movimientoGeneralDto.getNumeroCuenta());
			if (cuentaEntity.isPresent()) {
				CuentaEntity cuentaEnt = cuentaEntity.get();
				MovimientoDto movimientoDto = MovimientoGeneralMapper.INSTANCE.genDtoToDto(movimientoGeneralDto);
				MovimientoEntity movimientoEntity = MovimientoMapper.INSTANCE.dtoToEntity(movimientoDto);
				movimientoEntity.setId(null);
				movimientoEntity.setCuentaEntity(cuentaEnt);
				movimientoEntity.setFecha(LocalDate.now());
				movimientoEntity.setEstado(true);
				validateMovimiento.validateValorMovimiento(movimientoGeneralDto.getTipoMovimiento(), movimientoGeneralDto.getValor(), 
						                                   movimientoEntity,cuentaEnt);			
				cuentaRepository.save(cuentaEnt);
				movimientoEntity = movimientoRepository.save(movimientoEntity);
				
				MovimientoDto movimientoDtoSave =  MovimientoMapper.INSTANCE.entityToDto(movimientoEntity);
				MovimientoGeneralDto movimientoGeneralDtoSave = MovimientoGeneralMapper.INSTANCE.dtoToGenDto(movimientoDtoSave);
				ResponseDto responseDto = ResponseDto.builder().menssage(HttpStatus.OK.name())
		                 				  .codeResponse(HttpStatus.OK.value()).objectResponse(movimientoGeneralDtoSave).build();
				return new ResponseEntity<>(responseDto,HttpStatus.OK);
			} else {
				log.error(Constants.ERROR_GET_CUENTA+Constants.NO_DATA_EXISTS);
				throw new CustomException(Constants.ERROR_GET_CUENTA+Constants.NO_DATA_EXISTS);
			}
		} catch(Exception ex) {
			log.error(Constants.ERROR_SAVE_MOVIMIENTO,ex);
			throw new CustomException(Constants.ERROR_SAVE_MOVIMIENTO+ex.getMessage());
		}
	}

	/**
	 * Consultar movimiento dado el numero de cuenta
	 */
	@Override
	public ResponseEntity<ResponseDto> getMovimiento(String numCuenta) {
		try {
			Optional<List<MovimientoEntity>> listMovimiento = movimientoRepository.findByCuentaEntity_numeroCuenta(numCuenta);
			if(listMovimiento.isPresent()) {
				List<MovimientoDto> listMovimientoDto = new ArrayList<>();
				List<MovimientoGeneralDto> listMovimientoGeneralDto = new ArrayList<>();
				
				listMovimiento.get().stream().forEach(movimientoEnt -> listMovimientoDto.add(MovimientoMapper.INSTANCE.entityToDto(movimientoEnt)));
				listMovimientoDto.stream().forEach(movimientoDto -> listMovimientoGeneralDto.add(MovimientoGeneralMapper.INSTANCE.dtoToGenDto(movimientoDto)));
				
				ResponseDto responseDto = ResponseDto.builder().menssage(HttpStatus.OK.name())
						                 .codeResponse(HttpStatus.OK.value()).objectResponse(listMovimientoGeneralDto).build();
				return new ResponseEntity<>(responseDto,HttpStatus.OK);
			}
			return new ResponseEntity<>(ResponseDto.builder().menssage(Constants.NO_DATA_EXISTS)
	                	.codeResponse(HttpStatus.NOT_FOUND.value()).objectResponse(null).build(),HttpStatus.NOT_FOUND);
		}catch(Exception ex) {
			log.error(Constants.ERROR_GET_MOVIMIENTO,ex);
			throw new CustomException(Constants.ERROR_GET_MOVIMIENTO+ex.getMessage());
		}
	}

	/**
	 * Actualizar un movimiento
	 */
	@Override
	public ResponseEntity<ResponseDto> updateMovimiento(String numCuenta, Integer idMovimiento, MovimientoGeneralDto movimientoGeneralDto) {
//		try {
//			ClienteEntity clienteEntity = ClienteEntity.builder().build(); 
//			ResponseEntity<ResponseDto> responseGestorCliente= gestorClienteFeign.getClienteByIdentificacion(identificacion);
//			if (Objects.nonNull(responseGestorCliente.getBody()) && responseGestorCliente.getStatusCode().equals(HttpStatus.OK)) {
//				Map<?, ?> linkedHashMap = (Map<?, ?>) responseGestorCliente.getBody().getObjectResponse();
//				Integer clienteId = (Integer)linkedHashMap.get("id");
//				if (Objects.nonNull(clienteId)) {
//					clienteEntity =ClienteEntity.builder().id(clienteId).build();  
//				}
//			} else {
//				log.error(Constants.ERROR_GET_CLIENTE,responseGestorCliente.getBody());
//				throw new CustomException(Constants.ERROR_GET_CLIENTE);
//			}
//			CuentaDto cuentaDto = CuentaGeneralMapper.INSTANCE.genDtoToDto(cuentaGeneralDto);
//			CuentaEntity cuentaEntitySent = CuentaMapper.INSTANCE.dtoToEntity(cuentaDto);
//			Optional<CuentaEntity> cuentaEntity = cuentaRepository.findByNumeroCuentaAndClienteEntity_PersonaEntity_identificacion(cuentaGeneralDto.getNumeroCuenta(),
//																																   identificacion);
//			cuentaEntitySent.setId(cuentaEntity.isPresent()?cuentaEntity.get().getId():null);
//			cuentaEntitySent.setClienteEntity(clienteEntity);
//			cuentaEntity = Optional.of(cuentaRepository.save(cuentaEntitySent));
//			CuentaDto cuentaDtoSave =  CuentaMapper.INSTANCE.entityToDto(cuentaEntity.get());
//			CuentaGeneralDto cuentaGeneralDtoSave = CuentaGeneralMapper.INSTANCE.dtoToGenDto(cuentaDtoSave);
//			cuentaGeneralDtoSave.setNumeroIdentificacion(cuentaGeneralDto.getNumeroIdentificacion());
//			ResponseDto responseDto = ResponseDto.builder().menssage(HttpStatus.OK.name())
//	                 				  .codeResponse(HttpStatus.OK.value()).objectResponse(cuentaGeneralDtoSave).build();
//			return new ResponseEntity<>(responseDto,HttpStatus.OK);
//		} catch(Exception ex) {
//			log.error(Constants.ERROR_UPDATE_CUENTA,ex);
//			throw new CustomException(Constants.ERROR_UPDATE_CUENTA+ex.getMessage());
//		}
		return null; //Borrar
	}

	/**
	 * Eliminar un movimiento
	 */
	@Override
	public ResponseEntity<ResponseDto> deleteMovimiento(String numCuenta, Integer idMovimiento) {
//		try {
//			ResponseEntity<ResponseDto> responseGestorCliente= gestorClienteFeign.getClienteByIdentificacion(identificacion);
//			if (Objects.isNull(responseGestorCliente.getBody()) || !responseGestorCliente.getStatusCode().equals(HttpStatus.OK)) {
//				log.error(Constants.ERROR_GET_CLIENTE,responseGestorCliente.getBody());
//				throw new CustomException(Constants.ERROR_GET_CLIENTE);
//			}
//			Optional<CuentaEntity> cuentaEntity = cuentaRepository.findByNumeroCuenta(numCuenta);
//			if (cuentaEntity.isPresent()) {
//				cuentaRepository.delete(cuentaEntity.get());
//				ResponseDto responseDto = ResponseDto.builder().menssage(Constants.DELETE_CUENTA)
//		                 .codeResponse(HttpStatus.OK.value()).objectResponse(null).build();
//				return new ResponseEntity<>(responseDto,HttpStatus.OK);
//			} else {
//				log.error(Constants.NO_DATA_EXISTS);
//				throw new CustomException(Constants.NO_DATA_EXISTS);
//			}
//		} catch(Exception ex) {
//			log.error(Constants.ERROR_DELETE_CUENTA,ex);
//			throw new CustomException(Constants.ERROR_DELETE_CUENTA+ex.getMessage());
//		}
		return null; //Borrar
	}

}
