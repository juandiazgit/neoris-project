package co.com.neoris.banco.comun.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import co.com.neoris.banco.comun.dto.PersonaDto;
import co.com.neoris.banco.comun.entity.PersonaEntity;

@Mapper
public interface PersonaMapper {
	
	PersonaMapper INSTANCE = Mappers.getMapper(PersonaMapper.class);
	
	@Mapping(target = "id", source = "id")
	@Mapping(target = "nombre", source = "nombre")
	@Mapping(target = "genero", source = "genero")
	@Mapping(target = "edad", source = "edad")
	@Mapping(target = "identificacion", source = "identificacion")
	@Mapping(target = "direccion", source = "direccion")
	@Mapping(target = "telefono", source = "telefono")
	PersonaDto entityToDto(PersonaEntity personaEntity);
	
	@InheritInverseConfiguration
	PersonaEntity dtoToEntity(PersonaDto personaDto);
	
}
