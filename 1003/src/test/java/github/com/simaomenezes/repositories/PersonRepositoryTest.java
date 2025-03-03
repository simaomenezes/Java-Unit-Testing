package github.com.simaomenezes.repositories;

import github.com.simaomenezes.model.Person;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class PersonRepositoryTest {

    @Autowired
    private PersonRepository repository;

    @DisplayName("Given Person Object when save then return saved person")
    @Test
    void testGivenPersonObject_whenSave_thenReturnPerson() {
        // Given / Arrange
        Person person1 = new Person(
                "Lucas",
                "Lima",
                "lucaslima@gmail.com",
                "Masculino",
                "Rua 1"
        );
        // When / Act

        Person p = repository.save(person1);

        // Then / Assert
        assertNotNull(p);// Can not be null
        assertTrue(p.getId() > 0);// Id must be greater than 0

    }
}
