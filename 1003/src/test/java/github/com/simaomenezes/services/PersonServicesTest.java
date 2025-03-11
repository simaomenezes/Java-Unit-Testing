package github.com.simaomenezes.services;

import github.com.simaomenezes.exceptions.ResourceNotFoundException;
import github.com.simaomenezes.model.Person;
import github.com.simaomenezes.repositories.PersonRepository;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class PersonServicesTest {

    @Mock
    private PersonRepository repository;

    @InjectMocks
    private PersonServices service;

    private Person person0;

    @BeforeEach
    public void setup() {
        // Given / Arrange
        person0 = new Person(
                "Lucas",
                "Mendes",
                "lucaslima@gmail.com",
                "Rua XX",
                "Male"
        );
    }


    @DisplayName("Given Person Object when Save Person then return Person")
    @Test
    void testGivenPersonObject_whenSavePerson_thenReturnPerson() {

        // Given / Arrange
        given(repository.findByEmail(anyString())).willReturn(Optional.empty());
        given(repository.save(person0)).willReturn(person0);

        // When / Act
        Person personSaved  = service.create(person0);

        // Then / Assert
        assertNotNull(personSaved);
        assertEquals("lucaslima@gmail.com", personSaved.getEmail());
    }

    @DisplayName("Given Existing Email when Save Person then throws Exception")
    @Test
    void testGivenExistingEmail_whenSavePerson_thenThrowsException() {

        // Given / Arrange
        given(repository.findByEmail(anyString())).willReturn(Optional.of(person0));

        // When / Act
        assertThrows(ResourceNotFoundException.class, () -> {
            service.create(person0);
        });

        // Then / Assert
        verify(repository, never()).save(any(Person.class));
    };


    @DisplayName("Given Persons List when Find All Persons then return Persons List")
    @Test
    void testGivenPersonsList_whenFindAllPersons_thenReturnPersonsList(){
        // Given / Arrange
        Person p = new Person(
                "Eduardo",
                "Silva",
                "eduradosilva@gmail.com",
                "Rua 33",
                "Male"
        );
        given(repository.findAll()).willReturn(List.of(person0, p));

        // When / Act
        List<Person> personList = service.findAll();

        // Then / Assert
        assertNotNull(personList);
        assertEquals(2, personList.size());

    }


}
