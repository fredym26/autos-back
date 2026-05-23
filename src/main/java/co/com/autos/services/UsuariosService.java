package co.com.autos.services;

import co.com.autos.entities.Usuario;
import co.com.autos.mappers.UsuarioCreateMapper;
import co.com.autos.mappers.UsuarioMapper;
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
    public boolean crearUsuario(UsuarioCreateDTO usuarioCreateDTO) {
        try{

            Usuario usuario = usuarioCreateMapper.toEntity(usuarioCreateDTO);

            usuariosRepository.save(usuario);
            return true;
        }catch (Exception e){
            System.out.println("Error creando usuario: " + e);
            return false;
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
