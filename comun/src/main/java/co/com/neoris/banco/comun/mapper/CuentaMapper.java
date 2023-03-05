package co.com.neoris.banco.comun.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import co.com.neoris.banco.comun.dto.CuentaDto;
import co.com.neoris.banco.comun.entity.CuentaEntity;

@Mapper(uses= {ClienteMapper.class})
public interface CuentaMapper {
	
	CuentaMapper INSTANCE = Mappers.getMapper(CuentaMapper.class);
	
	@Mapping(target = "id", source = "id")
	@Mapping(target = "numeroCuenta", source = "numeroCuenta")
	@Mapping(target = "tipoCuenta", source = "tipoCuenta")
	@Mapping(target = "saldoInicial", source = "saldoInicial")
	@Mapping(target = "estado", source = "estado")
	@Mapping(target = "cliente", source = "clienteEntity")
	CuentaDto entityToDto(CuentaEntity clienteEntity);
	
	@InheritInverseConfiguration
	CuentaEntity dtoToEntity(CuentaDto clienteDto);
	
}
