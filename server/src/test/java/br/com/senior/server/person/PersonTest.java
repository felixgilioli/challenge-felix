package br.com.senior.server.person;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class PersonTest {

    @Test
    void equals() {
        var person1 = new Person();
        person1.setId(1L);

        var person2 = new Person();
        person2.setId(2L);

        var person3 = new Person();
        person3.setId(2L);

        assertNotEquals(person2, person1);
        assertEquals(person2, person3);
    }

}
