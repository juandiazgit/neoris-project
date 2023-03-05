package co.com.neoris.banco.comun.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import co.com.neoris.banco.comun.dto.MovimientoDto;
import co.com.neoris.banco.comun.entity.MovimientoEntity;

@Mapper(uses= {CuentaMapper.class})
public interface MovimientoMapper {
	
	MovimientoMapper INSTANCE = Mappers.getMapper(MovimientoMapper.class);
	
	@Mapping(target = "id", source = "id")
	@Mapping(target = "fecha", source = "fecha")
	@Mapping(target = "tipoMovimiento", source = "tipoMovimiento")
	@Mapping(target = "valor", source = "valor")
	@Mapping(target = "saldo", source = "saldo")
	@Mapping(target = "estado", source = "estado")
	@Mapping(target = "cuenta", source = "cuentaEntity")
	MovimientoDto entityToDto(MovimientoEntity movimientoEntity);
	
	@InheritInverseConfiguration
	MovimientoEntity dtoToEntity(MovimientoDto movimientoDto);

}
