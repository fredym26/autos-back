package co.com.autos.services;

import co.com.autos.entities.User;
import co.com.autos.mappers.UserCreateMapper;
import co.com.autos.mappers.UserMapper;
import co.com.autos.model.ResponseDTO;
import co.com.autos.model.UserCreateDTO;
import co.com.autos.model.UserDTO;
import co.com.autos.respositories.UserRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class UserService implements IUserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserCreateMapper userCreateMapper;

    @Autowired
    UserMapper userMapper;

    @Override
    public ResponseDTO createUser(UserCreateDTO userCreateDTO) {

        ResponseDTO responseDTO = new ResponseDTO();

        try{

            User user = userCreateMapper.toEntity(userCreateDTO);

            userRepository.save(user);

            responseDTO.setResultado(true);
            responseDTO.setCodigo(200);
            responseDTO.setNotificacion("Usuario creado");

            return responseDTO;
        }catch (Exception e){
            log.error("Error creando usuario: " + e);
            responseDTO.setResultado(false);
            responseDTO.setCodigo(201);
            responseDTO.setNotificacion("No se creo el usuario");
            return responseDTO;
        }
    }

    @Override
    public UserDTO findUser(Integer id) {

        try{
            return userMapper.toDTO(userRepository.findById(id).get());
        }catch (Exception e){
            log.error("Error obteniendo usuario: " + e);
            return null;
        }

    }
}
