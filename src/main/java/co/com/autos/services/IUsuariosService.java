package co.com.autos.services;

import co.com.autos.entities.Usuario;
import co.com.autos.model.RespuestaDTO;
import co.com.autos.model.UsuarioCreateDTO;
import co.com.autos.model.UsuarioDTO;


public interface IUsuariosService {

    public RespuestaDTO crearUsuario(UsuarioCreateDTO usuarioCreateDTO);
    public UsuarioDTO buscarUsuario (Integer id);
}
