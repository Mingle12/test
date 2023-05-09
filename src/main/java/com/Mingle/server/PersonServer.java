package com.Mingle.server;


import com.Mingle.person.Person;
import java.util.List;

public interface PersonServer {
    List<Person> getPersonList();
    void addPerson(Person person);
    void deletePeron(int id);
    void updatePerson(Person person);
}
