package co.com.autos.services;

import co.com.autos.model.CarDTO;
import co.com.autos.model.ResponseDTO;

public interface ICarService {

     ResponseDTO createCar(CarDTO carDTO);

     boolean deleteCar(Integer id);

     boolean validateLicensePlate(String placa);

     boolean validateYear(Integer anio);


}
