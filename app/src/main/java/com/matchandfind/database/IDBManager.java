package com.matchandfind.database;

import com.matchandfind.model.Person;

import java.util.List;

public interface IDBManager {

    void savePersons(List<Person> persons);

    List<Person> getPersons();

    void removePerson();

    void updatePerson();
}
