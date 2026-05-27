package co.com.autos.mappers;

import co.com.autos.entities.Car;
import co.com.autos.model.CarDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CarMapper {

    @Mapping(source = "user.userId", target = "userId")
    CarDTO toDTO(Car car);

    @Mapping(target = "user", ignore = true)
    Car toEntity(CarDTO carDTO);
}
