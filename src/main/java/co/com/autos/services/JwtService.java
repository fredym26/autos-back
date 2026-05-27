package co.com.autos.services;

import co.com.autos.entities.User;
import co.com.autos.jwt.JwtUtil;
import co.com.autos.model.ResponseDTO;
import co.com.autos.respositories.UserRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class JwtService implements  IJwtService{

    @Autowired
    UserRepository userRepository;

    @Autowired
    JwtUtil jwtUtil;

    @Override
    public ResponseDTO autenticar(String userName, String password) {

        ResponseDTO responseDTO = new ResponseDTO();

        try{
            // Busca el usuario en base de datos
            User user = userRepository.findByUserName(userName)
                    .orElseGet(() -> {
                        User u = new User();
                        u.setUserId(0);
                        return u;
                    });

            // Valida si el usuario existe
            if(user.getUserId().equals(0)){
                responseDTO.setResultado(false);
                responseDTO.setCodigo(403);
                responseDTO.setIdUsuario(0);
                responseDTO.setNotificacion("Usuario no existe");
                return responseDTO;
            }

            if(user.getUserName().equals(userName) && user.getPassword().equals(password)) {

                // Genera token
                String token = jwtUtil.generarToken(userName);
                responseDTO.setResultado(true);
                responseDTO.setCodigo(200);
                responseDTO.setIdUsuario(user.getUserId());
                responseDTO.setToken(token);
                responseDTO.setNotificacion("Correcto");
                return responseDTO;
            }else{

                responseDTO.setResultado(false);
                responseDTO.setCodigo(403);
                responseDTO.setIdUsuario(0);
                responseDTO.setNotificacion("Credenciales inválidas");
                return responseDTO;
            }



        }catch (Exception e){
            log.error("Error consultando usuario: " + userName);
            responseDTO.setResultado(false);
            responseDTO.setCodigo(403);
            responseDTO.setIdUsuario(0);
            responseDTO.setNotificacion("Error de autenticacion");
            return responseDTO;
        }

    }
}
