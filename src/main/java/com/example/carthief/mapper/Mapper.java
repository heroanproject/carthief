package com.example.carthief.mapper;

import com.example.carthief.dto.CarDto;
import com.example.carthief.entity.Car;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Mapper {

    public Mapper() {
    }

    public Car mapDtoToDealer(CarDto carDto){
        return new Car(carDto.getId(), carDto.getName(), carDto.getBrand(), carDto.getYear(), carDto.getKilometers(), carDto.getPrice());
    }

    public CarDto mapDealerToDto(Car car){
        return new CarDto(car);
    }

    public List<CarDto> mapDealerToDto(List<Car> cars){
        return cars.stream().map(CarDto::new).toList();
    }
}
