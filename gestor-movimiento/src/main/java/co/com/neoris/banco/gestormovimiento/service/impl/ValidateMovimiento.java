package co.com.neoris.banco.gestormovimiento.service.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import co.com.neoris.banco.comun.entity.CuentaEntity;
import co.com.neoris.banco.comun.entity.MovimientoEntity;
import co.com.neoris.banco.comun.exception.CustomException;
import co.com.neoris.banco.comun.repository.MovimientoRepository;
import co.com.neoris.banco.comun.util.Constants;
import co.com.neoris.banco.gestormovimiento.util.ConstantsMovimiento;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@Service
public class ValidateMovimiento {
	
	private final MovimientoRepository movimientoRepository;
	
	/**
	 * Valida el tipo y valor del movimiento
	 */
	public void validateValorMovimiento(String tipoMovimiento, Integer valor, MovimientoEntity movimientoEntity,
			                                                                  CuentaEntity cuentaEntity) {
		try {
			Integer saldo = 0;
			switch (tipoMovimiento) {
		    case ConstantsMovimiento.TIPO_MOVIMIENTO_DEPOSITO:
		    	saldo = cuentaEntity.getSaldoInicial()+valor;
		    	break;
		    case ConstantsMovimiento.TIPO_MOVIMIENTO_RETIRO:
				saldo = cuentaEntity.getSaldoInicial()-valor;
				if (cuentaEntity.getSaldoInicial() == 0 || saldo < 0) {
					log.error(ConstantsMovimiento.ERROR_SALDO_NO_DISPONIBLE);
					throw new CustomException(ConstantsMovimiento.ERROR_SALDO_NO_DISPONIBLE);
				}
				break;
		    default:
				log.error(ConstantsMovimiento.INVALID_TIPO_MOVIMIENTO);
				throw new CustomException(ConstantsMovimiento.INVALID_TIPO_MOVIMIENTO);
		    }
			cuentaEntity.setSaldoInicial(saldo);
			movimientoEntity.setTipoMovimiento(tipoMovimiento);
			movimientoEntity.setSaldo(saldo);
		} catch (Exception ex) {
			log.error(ConstantsMovimiento.ERROR_VALIDATE_MOVIMIENTO,ex);
			throw new CustomException(ConstantsMovimiento.ERROR_VALIDATE_MOVIMIENTO+ex.getMessage());
		}
	}
	
	/**
	 * Valida que la cuenta no sea modificada
	 */
	public void validateMovimientoByCuenta(String numCuentaSaved, String numCuentaUpdate) {
		try {
			if (Boolean.FALSE.equals(numCuentaSaved.equals(numCuentaUpdate))) {
				throw new CustomException(ConstantsMovimiento.ERROR_VALIDATE_MOVIMIENTO_BY_CUENTA);
			}
		} catch (Exception ex) {
			log.error(ConstantsMovimiento.ERROR_VALIDATE_MOVIMIENTO,ex);
			throw new CustomException(ConstantsMovimiento.ERROR_VALIDATE_MOVIMIENTO+ex.getMessage());
		}
	}
	
	/**
	 * Valida si existe un movimiento
	 */
	public MovimientoEntity getMovimientoById(Integer idMovimiento) {
		try {
			Optional<MovimientoEntity> movimientoEntity = movimientoRepository.findById(idMovimiento);
			if (movimientoEntity.isPresent()) {
				return movimientoEntity.get();
			} else {
				log.error(Constants.ERROR_GET_MOVIMIENTO+Constants.NO_DATA_EXISTS);
				throw new CustomException(Constants.ERROR_GET_MOVIMIENTO+Constants.NO_DATA_EXISTS);
			}
		} catch (Exception ex) {
			log.error(ConstantsMovimiento.ERROR_VALIDATE_MOVIMIENTO,ex);
			throw new CustomException(ConstantsMovimiento.ERROR_VALIDATE_MOVIMIENTO+ex.getMessage());
		}
	}
}
