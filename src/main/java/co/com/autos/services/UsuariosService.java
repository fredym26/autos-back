package co.com.autos.services;

import co.com.autos.entities.Usuario;
import co.com.autos.mappers.UsuarioCreateMapper;
import co.com.autos.mappers.UsuarioMapper;
import co.com.autos.model.RespuestaDTO;
import co.com.autos.model.UsuarioCreateDTO;
import co.com.autos.model.UsuarioDTO;
import co.com.autos.respositories.UsuariosRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class UsuariosService implements IUsuariosService{

    @Autowired
    UsuariosRepository usuariosRepository;

    @Autowired
    UsuarioCreateMapper usuarioCreateMapper;

    @Autowired
    UsuarioMapper usuarioMapper;

    @Override
    public RespuestaDTO crearUsuario(UsuarioCreateDTO usuarioCreateDTO) {

        RespuestaDTO respuestaDTO = new RespuestaDTO();

        try{

            Usuario usuario = usuarioCreateMapper.toEntity(usuarioCreateDTO);

            usuariosRepository.save(usuario);

            respuestaDTO.setResultado(true);
            respuestaDTO.setCodigo(200);
            respuestaDTO.setNotificacion("Usuario creado");

            return respuestaDTO;
        }catch (Exception e){
            log.error("Error creando usuario: " + e);
            respuestaDTO.setResultado(false);
            respuestaDTO.setCodigo(201);
            respuestaDTO.setNotificacion("No se creo el usuario");
            return respuestaDTO;
        }
    }

    @Override
    public UsuarioDTO buscarUsuario(Integer id) {

        try{
            return usuarioMapper.toDTO(usuariosRepository.findById(id).get());
        }catch (Exception e){
            log.error("Error obteniendo usuario: " + e);
            return null;
        }

    }
}
