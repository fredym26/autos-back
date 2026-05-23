package co.com.autos.model;

import co.com.autos.entities.Auto;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class UsuarioDTO {

    private Integer usuarioId;
    private String nombre;
    private String nombreUsuario;
    private String email;
    private String rol;
    private LocalDateTime fechaRegistro;
    private List<Auto> autos;

}
