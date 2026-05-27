package co.com.autos.model;

import lombok.Data;

@Data
public class UserCreateDTO {

    private String fullName;
    private String userName;
    private String email;
    private String role;
    private String password;
}
