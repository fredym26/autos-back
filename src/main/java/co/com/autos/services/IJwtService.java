package co.com.autos.services;

import co.com.autos.model.ResponseDTO;

public interface IJwtService {
    ResponseDTO autenticar(String userName, String password);
}
