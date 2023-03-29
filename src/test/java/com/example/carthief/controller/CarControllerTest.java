package com.example.carthief.controller;


import com.example.carthief.entity.Car;

import com.example.carthief.repository.CarRepository;


import com.fasterxml.jackson.databind.ObjectMapper;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;


import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;


import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = CarController.class)
class CarControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CarRepository carRepository;


    @Test
    public void testGetCars () throws Exception {
        Car car1 = new Car();
        car1.setId(1L);
        car1.setName("Honda");
        car1.setPrice(BigDecimal.valueOf(1000));
        car1.setBrand("Civik");
        car1.setKilometers(60000);
        car1.setYear(2012);

        when(carRepository.findAll()).thenReturn(List.of(car1));

        mockMvc.perform(get("/cars")).andExpect(status().isOk());

    }

    @Test
    void getCarsByIdThatDoesNotExistsReturns404 () throws Exception {
        mockMvc.perform(get("/cars/2")).andExpect(status().isNotFound());
    }

    @Test
    void getCarsByIdReturns200OK () throws Exception {
        Car car1 = new Car();
        car1.setId(1L);
        car1.setName("Honda");
        car1.setPrice(BigDecimal.valueOf(1000));
        car1.setBrand("Civik");
        car1.setKilometers(60000);
        car1.setYear(2012);

        String expectedJson = """
                {"id":1,"name":"Honda","price":1000,"brand":"Civik","kilometers":60000,"year":2012}""";

        when(carRepository.findById(1L)).thenReturn(Optional.of(car1));

        var result = mockMvc.perform(get("/cars/1")).andExpect(status().isOk()).andReturn();


        Assertions.assertThat(result.getResponse().getStatus()).isEqualTo(200);
        Assertions.assertThat(result.getResponse().getContentAsString()).isEqualTo(expectedJson);
    }

    @Test
    void getCarByID200WithJsonBody () throws Exception {
        Car car1 = new Car();
        car1.setId(1L);
        car1.setName("Honda");
        car1.setPrice(BigDecimal.valueOf(1000));
        car1.setBrand("Civik");
        car1.setKilometers(60000);
        car1.setYear(2012);


        when(carRepository.findById(1L)).thenReturn(Optional.of(car1));

        var result = mockMvc.perform(get("/cars/1")).andExpect(status().isOk()).andExpect(ResponseBodyMatchers.responseBody().containsObjectAsJson(car1, Car.class));
    }

    @Test
    public void testUpdateCar () throws Exception {
        Car car = new Car();
        car.setId(1L);
        car.setName("Honda");
        car.setBrand("Civic");
        car.setPrice(BigDecimal.valueOf(20000));
        car.setKilometers(50000);
        car.setYear(2019);

        given(carRepository.findById(1L)).willReturn(Optional.of(car));
        given(carRepository.save(car)).willReturn(car);

        Car updatedCar = new Car();
        updatedCar.setName("Honda Updated");
        updatedCar.setBrand("Civic Updated");
        updatedCar.setPrice(BigDecimal.valueOf(25000));
        updatedCar.setKilometers(60000);
        updatedCar.setYear(2020);

        String updatedCarJson = new ObjectMapper().writeValueAsString(updatedCar);

        mockMvc.perform(put("/cars/{id}", car.getId()).contentType(MediaType.APPLICATION_JSON).content(updatedCarJson)).andExpect(status().isOk());

    }


    @Test
    void deleteAnItemFromInventoryShouldRemoveIt() throws Exception {
        var id = 1L;

        var car = new Car();
        car.setName("Test");
        car.setId(id);

        when(carRepository.findById(id)).thenReturn(Optional.of(car));

        mockMvc.perform(delete("/cars/1")).andExpect(status().isOk());

    }
}