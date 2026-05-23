package co.com.autos.services;

import co.com.autos.entities.Auto;
import co.com.autos.entities.Usuario;
import co.com.autos.mappers.AutoMapper;
import co.com.autos.model.AutoDTO;
import co.com.autos.respositories.AutosRepository;
import co.com.autos.respositories.UsuariosRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class AutosService implements IAutosService{

    @Autowired
    AutosRepository autosRepository;

    @Autowired
    AutoMapper autoMapper;

    @Autowired
    UsuariosRepository usuariosRepository;

    @Override
    public boolean registrarAuto(AutoDTO autoDTO) {

        try {

            Usuario usuario = usuariosRepository.findById(autoDTO.getUsuarioId()).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
            Auto auto = autoMapper.toEntity(autoDTO);
            auto.setUsuario(usuario);

            autosRepository.save(auto);
            return true;
        }catch (Exception e){
            log.error("Error registrando o editando auto: " + e);
            return false;
        }
    }

    @Override
    public boolean eliminarAuto(Integer id) {

        try{
            if(!autosRepository.existsById(id)){
                log.info("No se encontro ningun auto con id: " + id);
                return false;
            }
            autosRepository.deleteById(id);
            return true;
        }catch (Exception e){
            log.error("Error eliminando auto: " + e);
            return false;
        }

    }


}
