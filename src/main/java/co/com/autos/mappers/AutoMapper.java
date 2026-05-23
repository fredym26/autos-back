package co.com.autos.mappers;

import co.com.autos.entities.Auto;
import co.com.autos.model.AutoDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AutoMapper {

    @Mapping(source = "usuario.usuarioId", target = "usuarioId")
    AutoDTO toDTO(Auto auto);

    @Mapping(target = "usuario", ignore = true)
    Auto toEntity(AutoDTO autoDTO);
}
