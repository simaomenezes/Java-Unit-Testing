package github.com.simaomenezes.service;

import github.com.simaomenezes.model.Person;

public class PersonService implements IPersonService {
    @Override
    public Person createPerson(Person person) {

        if(person.getEmail() == null || person.getEmail().isBlank()) throw new IllegalArgumentException ("The Person e-Mail is null or empty!");

        return person;
    }
}
