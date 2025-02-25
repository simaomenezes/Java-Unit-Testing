package github.com.simaomenezes;

import github.com.simaomenezes.model.Person;
import github.com.simaomenezes.service.IPersonService;
import github.com.simaomenezes.service.PersonService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class PersonTest {
    IPersonService personService;
    Person person;
    @BeforeEach
    void setup(){
        personService = new PersonService();
        person = new Person(
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
        Person actual = personService.createPerson(person);
        // Then / Assert
        assertNotNull(actual, () -> "The createPerson() should not have returned null!");
    }
}
