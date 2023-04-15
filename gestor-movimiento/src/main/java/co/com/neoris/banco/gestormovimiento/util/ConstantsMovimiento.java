package co.com.neoris.banco.gestormovimiento.util;

public class ConstantsMovimiento {

	private ConstantsMovimiento() {}
	
	public static final String TIPO_MOVIMIENTO_DEPOSITO = "Deposito";
	public static final String TIPO_MOVIMIENTO_RETIRO = "Retiro";
	
	public static final String INVALID_TIPO_MOVIMIENTO = "Tipo de movimiento invalido.";
	public static final String ERROR_VALIDATE_MOVIMIENTO = "Error al validar el movimiento.";
	
	public static final String ERROR_SALDO_NO_DISPONIBLE = "Saldo no disponible.";
	
	public static final String ERROR_VALIDATE_MOVIMIENTO_BY_CUENTA = "La cuenta del movimiento no se puede modificar.";
}
