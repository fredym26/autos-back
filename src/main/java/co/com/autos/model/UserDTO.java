package co.com.autos.model;

import co.com.autos.entities.Car;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class UserDTO {

    private Integer userId;
    private String fullName;
    private String userName;
    private String email;
    private String role;
    private LocalDateTime registrationDate;
    private List<Car> cars;

}
