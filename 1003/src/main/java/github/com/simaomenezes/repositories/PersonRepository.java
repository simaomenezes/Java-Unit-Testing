package github.com.simaomenezes.repositories;

import github.com.simaomenezes.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person, Long> {
    Optional<Person> findByEmail(String email);

    // Define custom query using JPQL with index parameters
    @Query("select p from Person p where p.firstName =?1 and p.lastName =?2")
    Person findByPJQL(String firstName, String lastName);


    // Define custom query using JPQL with named parameters
    @Query("select p from Person p where p.firstName =:firstName and p.lastName =:lastName")
    Person findByPJQLNamedParam(@Param("firstName") String firstName, @Param("lastName") String lastName);


    // Define custom query using Native SQL with index parameters
    @Query(value = "select * from person where first_name =?1 and last_name =?2", nativeQuery = true)
    Person findByNativeSQL(String firstName, String lastName);
}