package co.com.autos.model;

import lombok.Data;

@Data
public class CarDTO {

    private Integer carId;
    private String brand;
    private String model;
    private Integer year;
    private Integer userId;
    private String license_plate;
    private String color;
}
