package co.com.autos.services;

import co.com.autos.model.RespuestaDTO;

public interface IJwtService {
    RespuestaDTO autenticar(String userName, String password);
}
