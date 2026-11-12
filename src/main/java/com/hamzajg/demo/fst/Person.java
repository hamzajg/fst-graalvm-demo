package com.hamzajg.demo.fst;

import java.io.Serializable;
import java.util.Objects;

/**
 * A plain serializable value object used to demonstrate FST.
 *
 * <p>FST needs a no-argument constructor and JavaBean-style accessors, so the
 * class is deliberately not a record.
 */
public class Person implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;
    private int age;

    public Person() {
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Person)) {
            return false;
        }
        Person person = (Person) other;
        return age == person.age && Objects.equals(name, person.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }

    @Override
    public String toString() {
        return "Person{name='" + name + "', age=" + age + "}";
    }
}
