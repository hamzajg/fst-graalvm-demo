package com.hamzajg.demo.fst;

import org.nustaq.serialization.FSTConfiguration;

/**
 * Serializes a {@link Person} with FST and reads it back.
 *
 * <p>The point of the demo is the round trip: FST turns the object into bytes
 * without any schema, and the bytes turn back into an equal object.
 */
public class BootstrapApp {

    public static void main(String... args) {
        FSTConfiguration configuration = FSTConfiguration.createDefaultConfiguration();

        Person original = new Person("Ada Lovelace", 36);
        byte[] bytes = configuration.asByteArray(original);
        Person copy = (Person) configuration.asObject(bytes);

        System.out.println("Original:   " + original);
        System.out.println("Bytes:      " + bytes.length);
        System.out.println("Round trip: " + copy);
        System.out.println("Equal:      " + original.equals(copy));
    }
}
