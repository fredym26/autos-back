package co.com.autos.mappers;

import co.com.autos.entities.Usuario;
import co.com.autos.model.UsuarioCreateDTO;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface UsuarioCreateMapper {

    Usuario toEntity(UsuarioCreateDTO usuarioCreateDTO);

}
