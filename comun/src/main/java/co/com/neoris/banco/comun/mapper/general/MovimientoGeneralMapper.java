package co.com.neoris.banco.comun.mapper.general;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import co.com.neoris.banco.comun.dto.MovimientoDto;
import co.com.neoris.banco.comun.dto.general.MovimientoGeneralDto;

@Mapper
public interface MovimientoGeneralMapper {
	
	MovimientoGeneralMapper INSTANCE = Mappers.getMapper(MovimientoGeneralMapper.class);
	
	@Mapping(target = "id", source = "id")
	@Mapping(target = "fecha", source = "fecha")
	@Mapping(target = "tipoMovimiento", source = "tipoMovimiento")
	@Mapping(target = "valor", source = "valor")
	@Mapping(target = "saldo", source = "saldo")
	@Mapping(target = "estado", source = "estado")
	@Mapping(target = "numeroCuenta", source = "cuenta.numeroCuenta")
	@Mapping(target = "nombreCliente", source = "cuenta.cliente.persona.nombre")
	MovimientoGeneralDto dtoToGenDto(MovimientoDto movimientoDto);
	
	@InheritInverseConfiguration
	MovimientoDto genDtoToDto(MovimientoGeneralDto movimientoGeneralDto);
	
}
