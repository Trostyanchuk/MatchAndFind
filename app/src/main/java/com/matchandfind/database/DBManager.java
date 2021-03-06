package com.matchandfind.database;

import com.matchandfind.MatchAndFindApp;
import com.matchandfind.database.model.DBPerson;
import com.matchandfind.model.Person;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

public class DBManager implements IDBManager {

    @Override
    public void clearDB() {
        Realm instance = getInstance();
        instance.beginTransaction();
        instance.clear(DBPerson.class);
        instance.commitTransaction();
    }

    @Override
    public void savePersons(List<Person> persons) {
        List<DBPerson> dbPersonsList = new ArrayList<>();
        for (Person person : persons) {
            dbPersonsList.add(ModelTranslator.translatePersonToDbObject(person));
        }

        Realm instance = getInstance();
        instance.beginTransaction();
        instance.copyToRealm(dbPersonsList);
        instance.commitTransaction();
    }

    @Override
    public List<Person> getPersons() {
        List<Person> listToReturn = new ArrayList<>();
        RealmResults<DBPerson> realmResults = getInstance().where(DBPerson.class).findAll();
        for (DBPerson dbPerson : realmResults) {
            listToReturn.add(ModelTranslator.translateDBPersonToPerson(dbPerson));
        }
        return listToReturn;
    }


    @Override
    public void removePerson(int externalId) {
        Realm instance = getInstance();
        instance.beginTransaction();
        RealmResults<DBPerson> realmResults = instance
                .where(DBPerson.class)
                .equalTo("externalId", externalId)
                .findAll();
        realmResults.remove(0);
        instance.commitTransaction();
    }

    @Override
    public void updatePerson() {

    }

    private Realm getInstance() {
        return Realm.getInstance(MatchAndFindApp.getInstance());
    }
}
