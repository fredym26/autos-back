package co.com.autos.model;

import lombok.Data;

@Data
public class ResponseDTO {

    private boolean resultado;
    private Integer codigo ;
    private String token;
    private Integer idUsuario;
    private String notificacion;
}
