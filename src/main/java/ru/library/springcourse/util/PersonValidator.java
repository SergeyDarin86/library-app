package ru.library.springcourse.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.library.springcourse.dao.PersonDAO;
import ru.library.springcourse.models.Person;

@Component
public class PersonValidator implements Validator {

    private final PersonDAO personDAO;

    @Autowired
    public PersonValidator(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Person person = (Person) target;

        if (personDAO.show(person.getFullName()).isPresent()) {
            if (personDAO.show(person.getFullName()).get().getPersonId() != person.getPersonId()) {
                errors.rejectValue("fullName", "", "This fullName already is used by someone");
            }
        }

    }

}
