package github.com.simaomenezes.repositories;

import github.com.simaomenezes.model.Person;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

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

    @DisplayName("Given Person List when list all then return list")
    @Test
    void testGivenPersonLis_whenListAll_thenReturnList() {
        // Given / Arrange
        Person person1 = new Person(
                "Lucas",
                "Lima",
                "lucaslima@gmail.com",
                "Masculino",
                "Rua 1"
        );
        Person person2 = new Person(
                "Lucas1",
                "Lima1",
                "lucaslima1@gmail.com",
                "Masculino",
                "Rua 11"
        );
        repository.save(person1);
        repository.save(person2);
        // When / Act
        List<Person> personList = repository.findAll();
        // Then / Assert
        assertNotNull(personList);
        assertEquals(2, personList.size());
    }

    @DisplayName("Given Person Object when find by id then return person object")
    @Test
    void testGivenPersonObject_whenFindById_thenReturnPersonObject() {
        // Given / Arrange
        Person person1 = new Person(
                "Murilo",
                "Lima",
                "murilo@gmail.com",
                "Masculino",
                "Rua 4"
        );
        repository.save(person1);
        // When / Act
        Person p = repository.findById(person1.getId()).isPresent() ? repository.findById(person1.getId()).get() : null;
        // Then / Assert
        assertNotNull(p);
        assertEquals(p.getId(), person1.getId());
    }
}
