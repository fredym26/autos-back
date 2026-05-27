package co.com.autos.mappers;


import co.com.autos.entities.User;
import co.com.autos.model.UserDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = CarMapper.class)
public interface UserMapper {


    UserDTO toDTO(User user);

    User toEntity(UserDTO userDTO);
}
