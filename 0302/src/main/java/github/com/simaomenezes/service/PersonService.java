package github.com.simaomenezes.service;

import github.com.simaomenezes.model.Person;

public class PersonService implements IPersonService {
    @Override
    public Person createPerson(Person person) {

        return new Person();
    }
}
