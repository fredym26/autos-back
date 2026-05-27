package co.com.autos.controllers;

import co.com.autos.jwt.JwtUtil;
import co.com.autos.model.ResponseDTO;
import co.com.autos.model.UserCreateDTO;
import co.com.autos.services.IJwtService;
import co.com.autos.services.IUserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RestController
@CrossOrigin(value = "http://localhost:5173")
@RequestMapping("/authentication")
public class AuthenticationController {

    @Autowired
    IJwtService jwtService;

    @Autowired
    IUserService userService;

    private final JwtUtil jwtUtil;

    public AuthenticationController(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseDTO> login(@RequestParam String username, @RequestParam String password) {
        System.out.println("Autenticar: " + username + " " + password);
        ResponseDTO responseDTO = jwtService.autenticar(username, password);
        try {
            if (responseDTO.isResultado()) {
                return ResponseEntity.ok(responseDTO);
            }
        }catch (Exception e){
            System.out.println("ERROR:" + e);
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(responseDTO);
    }

    @PostMapping("/createUser")
    public ResponseDTO createUser(@RequestBody UserCreateDTO usuario){
        log.info("REGISTRANDO usuario: " );
        return userService.createUser(usuario);
    }
}
