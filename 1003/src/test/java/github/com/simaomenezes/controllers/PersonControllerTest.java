package github.com.simaomenezes.controllers;

import static org.hamcrest.CoreMatchers.is;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import github.com.simaomenezes.model.Person;
import github.com.simaomenezes.services.PersonServices;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest
public class PersonControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @MockitoBean
    private PersonServices service;

    private Person person;

    @BeforeEach
    public void setup(){
        person = new Person(
                "Fulano",
                "Any",
                "fulano@gmail.com",
                "Address XXX",
                "Male"
        );
    }

    @Test
    @DisplayName("Given Person Object when Create Person then Return Saved Person")
    void testGivenPersonObject_whenCreatePerson_thenReturnSavedPerson() throws JsonProcessingException, Exception {
        // Given / Arrange
        given(service.create(any(Person.class)))
                .willAnswer((invocationOnMock -> invocationOnMock.getArgument(0)));

        // When / Act
        ResultActions response = mockMvc.perform(post("/person")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(person)));

        // Then / Assert
        response.andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", is(person.getFirstName())))
                .andExpect(jsonPath("$.lastName", is(person.getLastName())))
                .andExpect(jsonPath("$.email", is(person.getEmail())));
    }

    @Test
    @DisplayName("Given List of Persons when findAll Persons then Return Persons List")
    void testGivenPersonsList_whenFindAllPersons_thenReturnPersonsList() throws Exception {
        // Given / Arrange
        List<Person> personList = new ArrayList<>();
        personList.add(person);
        personList.add(
                new Person(
                        "Raimundo",
                        "Any",
                        "fulano@gmail.com",
                        "Address XXX",
                        "Male"
                )
        );

        given(service.findAll()).willReturn(personList);

        // When / Act
        ResultActions response = mockMvc.perform(get("/person"));

        // Then / Assert

        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.size()", is(personList.size())));

    }

    @Test
    @DisplayName("Given personId when findById then return Person Object")
    void testGivenPersonId_whenFindById_thenReturnPersonObject() throws Exception {
        // Given / Arrange
        Long personId = 1L;
        given(service.findById(personId)).willReturn(person);

        // When / Act
        ResultActions response = mockMvc.perform(get("/person/{id}", personId));

        // Then / Assert
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.firstName", is(person.getFirstName())))
                .andExpect(jsonPath("$.lastName", is(person.getLastName())))
                .andExpect(jsonPath("$.email", is(person.getEmail())));
    }


}
