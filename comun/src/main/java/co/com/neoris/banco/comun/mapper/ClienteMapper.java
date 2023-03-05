package co.com.neoris.banco.comun.mapper;

import org.mapstruct.Mapping;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import co.com.neoris.banco.comun.dto.ClienteDto;
import co.com.neoris.banco.comun.entity.ClienteEntity;

@Mapper(uses= {PersonaMapper.class})
public interface ClienteMapper {

	ClienteMapper INSTANCE = Mappers.getMapper(ClienteMapper.class);
	
	@Mapping(target = "id", source = "id")
	@Mapping(target = "clienteIden", source = "clienteIden")
	@Mapping(target = "contrasena", source = "contrasena")
	@Mapping(target = "estado", source = "estado")
	@Mapping(target = "persona", source = "personaEntity")
	ClienteDto entityToDto(ClienteEntity clienteEntity);
	
	@InheritInverseConfiguration
	ClienteEntity dtoToEntity(ClienteDto clienteDto);
}
