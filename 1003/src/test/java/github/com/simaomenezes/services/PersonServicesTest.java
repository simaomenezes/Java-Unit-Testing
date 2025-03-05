package github.com.simaomenezes.services;

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

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

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

}
