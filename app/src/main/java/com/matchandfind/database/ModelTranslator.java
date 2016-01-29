package com.matchandfind.database;

import com.matchandfind.database.model.DBPerson;
import com.matchandfind.model.Person;

public final class ModelTranslator {

    public static DBPerson translatePersonToDbObject(Person person) {
        DBPerson dbPerson = new DBPerson();
        dbPerson.setExternalId(person.getId());
        dbPerson.setPhoto(person.getPhoto());
        dbPerson.setStatus(person.getStatus());
        dbPerson.setLat(person.getLat());
        dbPerson.setLon(person.getLon());
        return dbPerson;
    }

    public static Person translateDBPersonToPerson(DBPerson dbPerson) {
        Person person = new Person();
        person.setStatus(dbPerson.getStatus());
        person.setId(dbPerson.getExternalId());
        person.setPhoto(dbPerson.getPhoto());
        person.setLat(dbPerson.getLat());
        person.setLon(dbPerson.getLon());
        return person;
    }
}
