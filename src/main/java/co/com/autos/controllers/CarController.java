package co.com.autos.controllers;

import co.com.autos.model.CarDTO;
import co.com.autos.model.ResponseDTO;
import co.com.autos.model.UserCreateDTO;
import co.com.autos.model.UserDTO;
import co.com.autos.services.ICarService;
import co.com.autos.services.IUserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Log4j2
@RequestMapping("/api/cars")
@CrossOrigin(value = "http://localhost:5173")
public class CarController {

    @Autowired
    IUserService userService;

    @Autowired
    ICarService carService;

    @PostMapping("/saveUser")
    public ResponseDTO saveUser(@RequestBody UserCreateDTO usuario){
        log.info("REGISTRANDO usuario: " );
        return userService.createUser(usuario);
    }

    @PostMapping("/createCar")
    public ResponseDTO createCar(@RequestBody CarDTO carDTO){
        log.info("REGISTRANDO auto: " );
        return carService.createCar(carDTO);
    }

    @GetMapping("/findUser/{id}")
    public UserDTO findUser(@PathVariable Integer id) {
        return userService.findUser(id);
    }


    @DeleteMapping("/deleteCar/{id}")
    public boolean deleteCar(@PathVariable Integer id) {
        return carService.deleteCar(id);
    }
}
