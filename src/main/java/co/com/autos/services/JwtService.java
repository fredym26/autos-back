package co.com.autos.services;

import co.com.autos.entities.Usuario;
import co.com.autos.jwt.JwtUtil;
import co.com.autos.model.RespuestaDTO;
import co.com.autos.respositories.UsuariosRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class JwtService implements  IJwtService{

    @Autowired
    UsuariosRepository usuariosRepository;

    @Autowired
    JwtUtil jwtUtil;

    @Override
    public RespuestaDTO autenticar(String userName, String password) {

        RespuestaDTO respuestaDTO = new RespuestaDTO();

        try{
            // Busca el usuario en base de datos
            Usuario usuario = usuariosRepository.findByNombreUsuario(userName)
                    .orElseGet(() -> {
                        Usuario u = new Usuario();
                        u.setUsuarioId(0);
                        return u;
                    });

            // Valida si el usuario existe
            if(usuario.getUsuarioId().equals(0)){
                respuestaDTO.setResultado(false);
                respuestaDTO.setCodigo(403);
                respuestaDTO.setIdUsuario(0);
                respuestaDTO.setNotificacion("Usuario no existe");
                return respuestaDTO;
            }

            if(usuario.getNombreUsuario().equals(userName) && usuario.getContraseña().equals(password)) {

                // Genera token
                String token = jwtUtil.generarToken(userName);
                respuestaDTO.setResultado(true);
                respuestaDTO.setCodigo(200);
                respuestaDTO.setIdUsuario(usuario.getUsuarioId());
                respuestaDTO.setToken(token);
                respuestaDTO.setNotificacion("Correcto");
                return respuestaDTO;
            }else{

                respuestaDTO.setResultado(false);
                respuestaDTO.setCodigo(403);
                respuestaDTO.setIdUsuario(0);
                respuestaDTO.setNotificacion("Credenciales inválidas");
                return respuestaDTO;
            }



        }catch (Exception e){
            log.error("Error consultando usuario: " + userName);
            respuestaDTO.setResultado(false);
            respuestaDTO.setCodigo(403);
            respuestaDTO.setIdUsuario(0);
            respuestaDTO.setNotificacion("Error de autenticacion");
            return respuestaDTO;
        }

    }
}
