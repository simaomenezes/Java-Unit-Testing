package github.com.simaomenezes.controllers;

import static org.hamcrest.CoreMatchers.is;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import github.com.simaomenezes.exceptions.ResourceNotFoundException;
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

    @Test
    @DisplayName("Given Invalid PersonId when findById then Return Not Found")
    void testGivenInvalidPersonId_WhenFindById_thenReturnNotFound() throws JsonProcessingException, Exception {

        // Given / Arrange
        long personId = 1L;
        given(service.findById(personId)).willThrow(ResourceNotFoundException.class);

        // When / Act
        ResultActions response = mockMvc.perform(get("/person/{id}", personId));

        // Then / Assert
        response.andExpect(status().isNotFound())
                .andDo(print());
    }

    @Test
    @DisplayName("Given Updated Person when Update then Return Updated Person Object")
    void testGivenUpdatedPerson_WhenUpdate_thenReturnUpdatedPersonObject() throws JsonProcessingException, Exception {

        // Given / Arrange
        long personId = 1L;
        given(service.findById(personId)).willReturn(person);
        given(service.update(any(Person.class)))
                .willAnswer((invocation) -> invocation.getArgument(0));

        // When / Act
        Person updatedPerson = new Person(
                "Rombo",
                "Silvestre",
                "rambo@silvestre.com",
                "Address XXX",
                "Male");

        ResultActions response = mockMvc.perform(put("/person")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(updatedPerson)));

        // Then / Assert
        response.
                andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.firstName", is(updatedPerson.getFirstName())))
                .andExpect(jsonPath("$.lastName", is(updatedPerson.getLastName())))
                .andExpect(jsonPath("$.email", is(updatedPerson.getEmail())));
    }

    @Test
    @DisplayName("Given Unexistent Person when Update then Return Not Found")
    void testGivenUnexistentPerson_WhenUpdate_thenReturnNotFound() throws JsonProcessingException, Exception {

        // Given / Arrange
        long personId = 1L;
        given(service.findById(personId)).willThrow(ResourceNotFoundException.class);
        given(service.update(any(Person.class)))
                .willAnswer((invocation) -> invocation.getArgument(1));

        // When / Act
        Person updatedPerson = new Person(
                "Rombo",
                "Silvestre",
                "rambo@silvestre.com",
                "Address XXX",
                "Male");

        ResultActions response = mockMvc.perform(put("/person")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(updatedPerson)));

        // Then / Assert
        response.andExpect(status().isNotFound()).andDo(print());
    }

    @Test
    @DisplayName("Given personId when Delete then Return NotContent")
    void testGivenPersonId_WhenDelete_thenReturnNotContent() throws JsonProcessingException, Exception {

        // Given / Arrange
        long personId = 1L;
        willDoNothing().given(service).delete(personId);

        // When / Act
        ResultActions response = mockMvc.perform(delete("/person/{id}", personId));

        // Then / Assert
        response.
                andExpect(status().isNoContent())
                .andDo(print());
    }
}
