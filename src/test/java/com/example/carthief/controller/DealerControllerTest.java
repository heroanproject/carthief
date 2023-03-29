package com.example.carthief.controller;

import com.example.carthief.entity.Dealer;
import com.example.carthief.repository.CarRepository;
import com.example.carthief.repository.DealerRepository;
import org.assertj.core.api.Assertions;
import org.glassfish.jaxb.core.v2.TODO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = DealerController.class)
class DealerControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private DealerController dealerController;

    @MockBean
    private DealerRepository dealerRepository;

    @MockBean
    private CarRepository carRepository;

    @Test
    void testGetDealer () throws Exception {
        Dealer dealer = new Dealer();
        dealer.setName("Robert");

        when(dealerRepository.findAll()).thenReturn(List.of(dealer));

        mockMvc.perform(get("/dealers"))
               .andExpect(status().isOk());
    }

    @Test
    void getDealersNotExistsReturn404 () throws Exception {

        Dealer dealer = new Dealer();

        when(dealerRepository.findAll()).thenReturn(List.of(dealer));
        mockMvc.perform(get("/dealers/2"))
               .andExpect(status().isNotFound());
    }

    @Test
    void getDealersByIdReturns200OK () throws Exception {
        Dealer dealer = new Dealer();
        dealer.setId(1L);
        dealer.setName("Robert");

        String expectedJson = """
                {"id":1,"name":"Robert"}""";

        when(dealerRepository.findById(1L)).thenReturn(Optional.of(dealer));

        var result = mockMvc.perform(get("/dealers/1"))
                            .andExpect(status().isOk())
                            .andReturn();

        Assertions.assertThat(result.getResponse()
                                    .getStatus())
                  .isEqualTo(200);
        Assertions.assertThat(result.getResponse()
                                    .getContentAsString())
                  .isEqualTo(expectedJson);

    }
}

