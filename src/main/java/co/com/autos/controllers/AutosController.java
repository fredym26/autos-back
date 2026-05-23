package co.com.autos.controllers;

import co.com.autos.model.AutoDTO;
import co.com.autos.model.UsuarioCreateDTO;
import co.com.autos.model.UsuarioDTO;
import co.com.autos.services.IAutosService;
import co.com.autos.services.IUsuariosService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Log4j2
@RequestMapping("/api/autos")
@CrossOrigin(value = "http://localhost:4200")
public class AutosController {

    @Autowired
    IUsuariosService usuariosService;

    @Autowired
    IAutosService autosService;

    @PostMapping("/guardarUsuario")
    public boolean guardarUsuario(@RequestBody UsuarioCreateDTO usuario){
        log.info("REGISTRANDO usuario: " );
        return usuariosService.crearUsuario(usuario);
    }

    @PostMapping("/registrarAuto")
    public boolean registrarAuto(@RequestBody AutoDTO autoDTO){
        log.info("REGISTRANDO auto: " );
        return autosService.registrarAuto(autoDTO);
    }

    @GetMapping("/obtenerUsuario/{id}")
    public UsuarioDTO obtenerUsuarioPorId(@PathVariable Integer id) {
        return usuariosService.buscarUsuario(id);
    }


    @DeleteMapping("/eliminarAuto/{id}")
    public boolean eliminarAuto(@PathVariable Integer id) {
        return autosService.eliminarAuto(id);
    }
}
