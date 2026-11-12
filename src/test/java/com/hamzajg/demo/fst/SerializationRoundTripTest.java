package com.hamzajg.demo.fst;

import org.junit.Test;
import org.nustaq.serialization.FSTConfiguration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;

public class SerializationRoundTripTest {

    @Test
    public void serializesAndDeserializesAnEqualPerson() {
        FSTConfiguration configuration = FSTConfiguration.createDefaultConfiguration();
        Person original = new Person("Ada Lovelace", 36);

        byte[] bytes = configuration.asByteArray(original);
        Person copy = (Person) configuration.asObject(bytes);

        assertEquals(original, copy);
        assertNotSame(original, copy);
        assertEquals("Ada Lovelace", copy.getName());
        assertEquals(36, copy.getAge());
    }
}
