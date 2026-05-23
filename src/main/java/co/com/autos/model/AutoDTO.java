package co.com.autos.model;

import lombok.Data;

@Data
public class AutoDTO {

    private Integer autoId;
    private String marca;
    private String modelo;
    private Integer anio;
    private Integer usuarioId;
    private String numeroPlaca;
    private String color;
}
