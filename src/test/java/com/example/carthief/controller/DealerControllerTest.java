package com.example.carthief.controller;

import com.example.carthief.entity.Car;
import com.example.carthief.entity.Dealer;
import com.example.carthief.repository.DealerRepository;
import com.example.carthief.security.SecurityConfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = DealerController.class)
@WithMockUser(authorities = {"ADMIN"})
@ContextConfiguration(classes = {SecurityConfig.class, DealerController.class})
class DealerControllerTest {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    @MockBean
    ClientRegistrationRepository clientRegistrationRepository;

    @MockBean
    private DealerController dealerController;

    @MockBean
    private DealerRepository dealerRepository;

    @BeforeEach
    public void setup(){
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @Test
    void testGetDealer () throws Exception {
        Dealer dealer = new Dealer();
        dealer.setId(1L);
        dealer.setName("Robert");
        dealer.setCars(null);
        when(dealerRepository.findAll()).thenReturn(List.of(dealer));

        var result = mockMvc.perform(get("/api/dealers/1").accept(MediaType.APPLICATION_JSON))
                            .andExpect(status().isOk())
                            .andReturn();

        Assertions.assertThat(result.getResponse().getStatus()).isEqualTo(200);

        Assertions.assertThat(dealer.getId()).isEqualTo(1L);
        Assertions.assertThat(dealer.getName()).isEqualTo("Robert");
        Assertions.assertThat(dealer.getCars()).isNull();
    }

    @Test
    void getDealersNotExistsReturn200 () throws Exception {

        mockMvc.perform(get("/api/dealers")).andExpect(status().isOk()).andExpect(content().json("[]"));
    }

    @Test
    void deleteAnItemFromInventoryShouldRemoveIt () throws Exception {
        var id = 1L;

        var dealers = new Dealer();
        dealers.setName("Test");
        dealers.setId(id);

        when(dealerRepository.findById(id)).thenReturn(Optional.of(dealers));

        mockMvc.perform(delete("/api/dealers/1")).andExpect(status().isOk());

    }

    @Test
    void testAddCarToDealer () {
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
