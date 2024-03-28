package ru.library.springcourse.models;

import javax.validation.constraints.*;

public class Person {

    //!!! Быть внимательным к именованию полей сущностей!!!
    // если в базе поле записано как person_id, то в сущности лучше именовать с использованием Camel
    // и дать название для поля как personId, а не просто "id"
    // в противном случае не получается полноценно извлечь значение поля и оно для всех будет равно 0
    // долго искал причину, почему приходит из запроса "Select * from Person" значения для id для всех
    // записей было равно 0 (((
    private int personId;

    // на данный момент regex для кириллицы
    @NotEmpty(message = "FullName should not be empty")
    @Size(min = 8, max = 100, message = "The FullName should be between 8 and 100 characters")
    @Pattern(regexp = "[А-ЯЁ][а-яё]+ [А-ЯЁ][а-яё]+ [А-ЯЁ][а-яё]+", message = "The FullName should have the format: Surname Name Patronymic")
    private String fullName;


    //TODO: посмотреть как настроить Pattern для числового поля, чтобы вводить только 4 символа для г.р.
//    @Pattern(regexp = "\\d{4}",message = "The format has to be: (4 numbers)")
    @Min(value = 1900, message = "Year of Birthday should be more than 1900")
    @NotNull(message = "Year of Birthday should not be empty")
    private Integer yearOfBirthday;

    // конструктор по умолчанию нужен для Spring
    public Person() {
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Integer getYearOfBirthday() {
        return yearOfBirthday;
    }

    public void setYearOfBirthday(Integer yearOfBirthday) {
        this.yearOfBirthday = yearOfBirthday;
    }
}
