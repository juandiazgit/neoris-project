package co.com.neoris.banco.comun.mapper.general;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import co.com.neoris.banco.comun.dto.CuentaDto;
import co.com.neoris.banco.comun.dto.general.CuentaGeneralDto;

@Mapper
public interface CuentaGeneralMapper {
	
	CuentaGeneralMapper INSTANCE = Mappers.getMapper(CuentaGeneralMapper.class);
	
	@Mapping(target = "id", source = "id")
	@Mapping(target = "numeroCuenta", source = "numeroCuenta")
	@Mapping(target = "tipoCuenta", source = "tipoCuenta")
	@Mapping(target = "saldoInicial", source = "saldoInicial")
	@Mapping(target = "estado", source = "estado")
	@Mapping(target = "numeroIdentificacion", source = "cliente.persona.identificacion")
	CuentaGeneralDto dtoToGenDto(CuentaDto cuentaDto);
	
	@InheritInverseConfiguration
	CuentaDto genDtoToDto(CuentaGeneralDto cuentaGeneralDto);
	
}
