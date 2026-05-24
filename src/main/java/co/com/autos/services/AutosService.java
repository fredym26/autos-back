package co.com.autos.services;

import co.com.autos.entities.Auto;
import co.com.autos.entities.Usuario;
import co.com.autos.mappers.AutoMapper;
import co.com.autos.model.AutoDTO;
import co.com.autos.model.RespuestaDTO;
import co.com.autos.respositories.AutosRepository;
import co.com.autos.respositories.UsuariosRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

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
    public RespuestaDTO registrarAuto(AutoDTO autoDTO) {

        RespuestaDTO respuestaDTO = new RespuestaDTO();

        try {

            Usuario usuario = usuariosRepository.findById(autoDTO.getUsuarioId()).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
            Auto auto = autoMapper.toEntity(autoDTO);
            auto.setUsuario(usuario);

            // Validar placa
            if(!this.validarPlaca(auto.getNumeroPlaca())){
                respuestaDTO.setResultado(false);
                respuestaDTO.setCodigo(201);
                respuestaDTO.setNotificacion("El formato de la placa no es valido, debe ser: ABC 123");
                return respuestaDTO;
            }

            // Validar año auto
            if(!this.validarAnio(auto.getAnio())){
                respuestaDTO.setResultado(false);
                respuestaDTO.setCodigo(201);
                respuestaDTO.setNotificacion("El año del auto no puede ser mayor al año en curso");
                return respuestaDTO;
            }

            autosRepository.save(auto);

            respuestaDTO.setResultado(true);
            respuestaDTO.setCodigo(200);
            respuestaDTO.setNotificacion("Auto registrado");
            return respuestaDTO;

        }catch (Exception e){
            log.error("Error registrando o editando auto: " + e);
            respuestaDTO.setResultado(false);
            respuestaDTO.setCodigo(201);
            respuestaDTO.setNotificacion("Error registrando el auto");
            return respuestaDTO;

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

    @Override
    public boolean validarPlaca(String placa) {
        String regex = "^[A-Z]{3}\\s[0-9]{3}$";
        return placa != null && placa.matches(regex);
    }

    @Override
    public boolean validarAnio(Integer anio) {

        LocalDate fechaActual = LocalDate.now();
        int anioActual = fechaActual.getYear();

        if(anio > anioActual){
            return false;
        }else {
            return true;
        }
    }


}
