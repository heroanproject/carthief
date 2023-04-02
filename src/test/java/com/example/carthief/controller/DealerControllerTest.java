package com.example.carthief.controller;

import com.example.carthief.entity.Car;
import com.example.carthief.entity.Dealer;
import com.example.carthief.repository.DealerRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.http.MediaType;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = DealerController.class)
@AutoConfigureMockMvc(addFilters = false)
class DealerControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private DealerController dealerController;

    @MockBean
    private DealerRepository dealerRepository;


    @Test
    void testGetDealer () throws Exception {
        Dealer dealer = new Dealer();
        dealer.setId(1L);
        dealer.setName("Robert");
        dealer.setCars(null);

        when(dealerRepository.findAll()).thenReturn(List.of(dealer));

        var result = mockMvc.perform(get("/dealers/1").accept(MediaType.APPLICATION_JSON))
                            .andExpect(status().isOk())
                            .andReturn();

        Assertions.assertThat(result.getResponse().getStatus()).isEqualTo(200);

        Assertions.assertThat(dealer.getId()).isEqualTo(1L);
        Assertions.assertThat(dealer.getName()).isEqualTo("Robert");
        Assertions.assertThat(dealer.getCars()).isNull();
    }

    @Test
    void getDealersNotExistsReturn404 () throws Exception {

        mockMvc.perform(get("/dealers")).andExpect(status().isOk()).andExpect(content().json("[]"));
    }

    @Test
    void deleteAnItemFromInventoryShouldRemoveIt () throws Exception {
        var id = 1L;

        var dealers = new Dealer();
        dealers.setName("Test");
        dealers.setId(id);

        when(dealerRepository.findById(id)).thenReturn(Optional.of(dealers));

        mockMvc.perform(delete("/dealers/1")).andExpect(status().isOk());

    }

    @Test
    public void testAddCarToDealer () {
        Long personId = 1L;

        Car car = new Car();
        car.setId(1L);
        Set<Car> cars = new HashSet<>();
        cars.add(car);

        Dealer dealer = new Dealer();
        dealer.setId(personId);
        dealer.setCars(cars);

        when(dealerRepository.findById(personId)).thenReturn(Optional.of(dealer));

        dealerController.addCarToDealer(personId, car);

        assertEquals(car.getId(), dealer.getId());

    }


}



