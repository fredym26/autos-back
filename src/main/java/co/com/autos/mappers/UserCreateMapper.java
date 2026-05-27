package co.com.autos.mappers;

import co.com.autos.entities.User;
import co.com.autos.model.UserCreateDTO;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface UserCreateMapper {

    User toEntity(UserCreateDTO userCreateDTO);

}
