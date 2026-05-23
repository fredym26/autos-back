package co.com.autos.model;

import lombok.Data;

@Data
public class UsuarioCreateDTO {

    private String nombre;
    private String nombreUsuario;
    private String email;
    private String rol;
    private String contraseña;
}
