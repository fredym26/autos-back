package co.com.autos.services;

import co.com.autos.entities.Car;
import co.com.autos.entities.User;
import co.com.autos.mappers.CarMapper;
import co.com.autos.model.CarDTO;
import co.com.autos.model.ResponseDTO;
import co.com.autos.respositories.CarRepository;
import co.com.autos.respositories.UserRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Log4j2
@Service
public class CarService implements ICarService {

    @Autowired
    CarRepository carRepository;

    @Autowired
    CarMapper carMapper;

    @Autowired
    UserRepository userRepository;

    @Override
    public ResponseDTO createCar(CarDTO carDTO) {

        ResponseDTO responseDTO = new ResponseDTO();

        try {
            if(carDTO.getCarId().equals(0)){
                carDTO.setCarId(null);
            }
            User user = userRepository.findById(carDTO.getUserId()).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
            Car car = carMapper.toEntity(carDTO);
            car.setUser(user);

            // Validar placa
            if(!this.validateLicensePlate(car.getLicense_plate())){
                responseDTO.setResultado(false);
                responseDTO.setCodigo(201);
                responseDTO.setNotificacion("El formato de la placa no es valido, debe ser: ABC 123");
                return responseDTO;
            }

            // Validar año auto
            if(!this.validateYear(car.getYear())){
                responseDTO.setResultado(false);
                responseDTO.setCodigo(201);
                responseDTO.setNotificacion("El año del auto no puede ser mayor al año en curso");
                return responseDTO;
            }

            carRepository.save(car);

            responseDTO.setResultado(true);
            responseDTO.setCodigo(200);
            responseDTO.setNotificacion("Auto registrado");
            return responseDTO;

        }catch (Exception e){
            log.error("Error registrando o editando auto: " + e);
            responseDTO.setResultado(false);
            responseDTO.setCodigo(201);
            responseDTO.setNotificacion("Error registrando el auto");
            return responseDTO;

        }
    }

    @Override
    public boolean deleteCar(Integer id) {

        try{
            if(!carRepository.existsById(id)){
                log.info("No se encontro ningun auto con id: " + id);
                return false;
            }
            carRepository.deleteById(id);
            return true;
        }catch (Exception e){
            log.error("Error eliminando auto: " + e);
            return false;
        }

    }

    @Override
    public boolean validateLicensePlate(String placa) {
        String regex = "^[A-Z]{3}\\s[0-9]{3}$";
        return placa != null && placa.matches(regex);
    }

    @Override
    public boolean validateYear(Integer anio) {

        LocalDate fechaActual = LocalDate.now();
        int anioActual = fechaActual.getYear();

        if(anio > anioActual){
            return false;
        }else {
            return true;
        }
    }


}
