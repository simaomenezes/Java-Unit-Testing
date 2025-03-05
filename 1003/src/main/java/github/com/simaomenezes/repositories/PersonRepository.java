package github.com.simaomenezes.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import github.com.simaomenezes.model.Person;

import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person, Long> {
    Optional<Person> findByEmail(String email);
}