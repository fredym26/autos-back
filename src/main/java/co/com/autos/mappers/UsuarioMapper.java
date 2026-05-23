package co.com.autos.mappers;


import co.com.autos.entities.Usuario;
import co.com.autos.model.UsuarioDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = AutoMapper.class)
public interface UsuarioMapper {


    UsuarioDTO toDTO(Usuario usuario);

    Usuario toEntity(UsuarioDTO usuarioDTO);
}
