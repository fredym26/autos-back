package co.com.autos.services;

import co.com.autos.model.ResponseDTO;
import co.com.autos.model.UserDTO;
import co.com.autos.model.UserCreateDTO;


public interface IUserService {

    public ResponseDTO createUser(UserCreateDTO userCreateDTO);
    public UserDTO findUser (Integer id);
}
