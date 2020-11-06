package com.andersen.patterns.creational.prototype;

public class PersonFactory {
    Person person;

    public PersonFactory(Person person) {
        setPrototype(person);
    }

    public void setPrototype(Person person) {
        this.person = person;
    }

    public Person makeCopy() {
        return (Person) person.clone();
    }
}
