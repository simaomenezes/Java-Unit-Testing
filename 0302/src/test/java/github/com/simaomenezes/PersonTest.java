package github.com.simaomenezes;

import github.com.simaomenezes.model.Person;
import github.com.simaomenezes.service.IPersonService;
import github.com.simaomenezes.service.PersonService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PersonTest {
    IPersonService personService;
    Person person;
    @BeforeEach
    void setup(){
        personService = new PersonService();
        person = new Person(
                1L,
                "Lucas Lima",
                "lucas@mail.com",
                "lucas.silva");
    }

    @DisplayName("When create a Person with success should return Person Object")
    @Test
    void testCreatePerson_WhenSuccess_ShouldReturnPersonObject(){
        // Given / Arrange
        IPersonService personService = new PersonService();
        // When / Act
        Person p = personService.createPerson(person);
        // Then / Assert
        assertNotNull(p, () -> "The createPerson() should not have returned null!");
    }


    @DisplayName("When Create a Person with Success Should Contains Valid Fields in Returned Person Object")
    @Test
    void testCreatePerson_WhenSucess_ShouldContainsValidFieldsInReturnedPersonObject() {

        // Given / Arrange
        // When / Act
        Person actual = personService.createPerson(person);
        // Then / Assert
        assertNotNull(person.getId(), () -> "Person ID is Missing");
        assertEquals(
                person.getName(),
                actual.getName(),
                () -> "The Person name is Incorrect!");
        assertEquals(
                person.getEmail(),
                actual.getEmail(),
                () -> "The Person email is Incorrect!");
        assertEquals(
                person.getUsername(),
                actual.getUsername(),
                () -> "The Person username is Incorrect!");
    }

    @DisplayName("When Create a Person with null e-Mail Should throw Exception")
    @Test
    void testCreatePerson_WhithNullEMail_ShouldThrowIllegalArgumentException() {
        // Given / Arrange
        person.setEmail(null);

        var expectedMessage = "The Person e-Mail is null or empty!";

        // When / Act & Then / Assert
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> service.createPerson(person),
                () -> "Empty e-Mail should have cause an IllegalArgumentException!"
        );

        // Then / Assert
        assertEquals(
                expectedMessage,
                exception.getMessage(),
                () -> "Exception error message is incorrect!");
    }
}
