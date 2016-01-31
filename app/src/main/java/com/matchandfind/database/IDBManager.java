package com.matchandfind.database;

import com.matchandfind.model.Person;

import java.util.List;

public interface IDBManager {

    void clearDB();

    void savePersons(List<Person> persons);

    List<Person> getPersons();

    void removePerson(int externalId);

    void updatePerson();
}
