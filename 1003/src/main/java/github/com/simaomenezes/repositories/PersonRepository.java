package github.com.simaomenezes.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import github.com.simaomenezes.model.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {}