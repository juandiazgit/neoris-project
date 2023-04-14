package co.com.neoris.banco.gestormovimiento.service.impl;

import org.springframework.stereotype.Service;

import co.com.neoris.banco.comun.entity.CuentaEntity;
import co.com.neoris.banco.comun.entity.MovimientoEntity;
import co.com.neoris.banco.comun.exception.CustomException;
import co.com.neoris.banco.gestormovimiento.util.Constants;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ValidateMovimiento {
	
	/**
	 * Valida el tipo y valor del movimiento
	 */
	public void validateValorMovimiento(String tipoMovimiento, Integer valor, MovimientoEntity movimientoEntity,
			                                                                  CuentaEntity cuentaEntity) {
		try {
			Integer saldo = 0;
			switch (tipoMovimiento) {
		    case Constants.TIPO_MOVIMIENTO_DEPOSITO:
		    	saldo = cuentaEntity.getSaldoInicial()+valor;
		    	break;
		    case Constants.TIPO_MOVIMIENTO_RETIRO:
				saldo = cuentaEntity.getSaldoInicial()-valor;
				if (cuentaEntity.getSaldoInicial() == 0 || saldo < 0) {
					log.error(Constants.ERROR_SALDO_NO_DISPONIBLE);
					throw new CustomException(Constants.ERROR_SALDO_NO_DISPONIBLE);
				}
				break;
		    default:
				log.error(Constants.INVALID_TIPO_MOVIMIENTO);
				throw new CustomException(Constants.INVALID_TIPO_MOVIMIENTO);
		    }
			cuentaEntity.setSaldoInicial(saldo);
			movimientoEntity.setTipoMovimiento(tipoMovimiento);
			movimientoEntity.setSaldo(saldo);
		} catch (Exception ex) {
			log.error(Constants.ERROR_VALIDATE_MOVIMIENTO,ex);
			throw new CustomException(Constants.ERROR_VALIDATE_MOVIMIENTO+ex.getMessage());
		}
	}
}
