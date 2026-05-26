package co.com.autos.controllers;

import co.com.autos.jwt.JwtUtil;
import co.com.autos.model.RespuestaDTO;
import co.com.autos.model.UsuarioCreateDTO;
import co.com.autos.services.IJwtService;
import co.com.autos.services.IUsuariosService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RestController
@CrossOrigin(value = "http://localhost:5173")
@RequestMapping("/autenticar")
public class AutenticacionController {

    @Autowired
    IJwtService jwtService;

    @Autowired
    IUsuariosService usuariosService;

    private final JwtUtil jwtUtil;

    public AutenticacionController(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public ResponseEntity<RespuestaDTO> login(@RequestParam String username, @RequestParam String password) {

        RespuestaDTO respuestaDTO = jwtService.autenticar(username, password);

        if (respuestaDTO.isResultado()) {
            return ResponseEntity.ok(respuestaDTO);
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(respuestaDTO);
    }

    @PostMapping("/guardarUsuario")
    public RespuestaDTO guardarUsuario(@RequestBody UsuarioCreateDTO usuario){
        log.info("REGISTRANDO usuario: " );
        return usuariosService.crearUsuario(usuario);
    }
}
