package co.com.autos.controllers;

import co.com.autos.jwt.JwtUtil;
import co.com.autos.model.RespuestaDTO;
import co.com.autos.services.IJwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/autenticar")
public class AutenticacionController {

    @Autowired
    IJwtService jwtService;

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
}
