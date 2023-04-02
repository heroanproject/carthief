package com.example.carthief.controller;

import static org.junit.jupiter.api.Assertions.*;

import com.example.carthief.entity.Car;
import com.example.carthief.entity.Person;
import com.example.carthief.repository.CarRepository;
import com.example.carthief.repository.PersonRepository;


import org.junit.jupiter.api.Test;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = PersonController.class)
@AutoConfigureMockMvc(addFilters = false)
class PersonControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PersonRepository personRepository;

    @MockBean
    private PersonController personController;
    @MockBean
    private CarRepository carRepository;


    @Test
    void getPerson () throws Exception {
        Person person = new Person();
        person.setName("Robert");
        person.setId(1L);

        when(personRepository.findAll()).thenReturn(List.of(person));

        mockMvc.perform(get("/persons")).andExpect(status().isOk());
    }

    @Test
    void getPersonByIdNotExistsReturn404 () throws Exception {

        mockMvc.perform(get("/persons"))
               .andExpect(status().isOk())
               .andExpect(content()
                       .json("[]"));
    }

    @Test
    void testGetName_all () {
        PersonController controller = new PersonController(personRepository, carRepository);

        List<Person> expectedPersons = new ArrayList<>();
        expectedPersons.add(new Person());
        expectedPersons.add(new Person());
        when(personRepository.findAll()).thenReturn(expectedPersons);


        List<Person> actualPersons = controller.getName();

        assertEquals(expectedPersons, actualPersons);
    }

    @Test
    void testAddNumber () throws Exception {
        PersonController controller = new PersonController(personRepository, carRepository);

        Person personToAdd = new Person();
        personToAdd.setName("John");

        controller.addNumber(personToAdd);

        var result = mockMvc.perform(get("/persons/1")).andExpect(status().isOk())
                            .andReturn();

      org.assertj.core.api.Assertions.assertThat(result.getResponse().getStatus()).isEqualTo(200);
    }

    @Test
    public void testDeleteOrg () throws Exception {

        Long id = 1L;

        var person = new Person();
        person.setName("test");
        person.setId(id);

        personController.deleteOrg(id);

        when(personRepository.findById(id)).thenReturn(Optional.of(person));
        mockMvc.perform(delete("/persons/1")).andExpect(status().isOk());
    }

    @Test
    public void testAddPersonToCar () {
        Long personId = 1L;

        Car car = new Car();
        car.setId(1L);

        Person person = new Person();
        person.setId(personId);
        person.setCar(car);

        when(personRepository.findById(personId)).thenReturn(Optional.of(person));

        personController.addPersonToCar(personId, car);

        assertEquals(car, person.getCar());

    }
}