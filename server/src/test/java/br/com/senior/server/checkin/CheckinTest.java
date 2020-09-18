package br.com.senior.server.checkin;

import br.com.senior.server.person.Person;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CheckinTest {

    @Test
    void equals() {
        var checkin1 = new Checkin();
        checkin1.setId(1L);

        var checkin2 = new Checkin();
        checkin2.setId(2L);

        var checkin3 = new Checkin();
        checkin3.setId(2L);

        assertNotEquals(checkin2, checkin1);
        assertEquals(checkin2, checkin3);
    }

    @Test
    @DisplayName("getHasVehicle returns false when is null")
    void getHasVehicleReturnsFalseWhenIsNull() {
        var checkin = new Checkin();
        checkin.setHasVehicle(null);
        assertFalse(checkin.getHasVehicle());

        checkin.setHasVehicle(true);
        assertTrue(checkin.getHasVehicle());

        checkin.setHasVehicle(false);
        assertFalse(checkin.getHasVehicle());
    }

    @Test
    @DisplayName("setHasVehicle sets false by default")
    void setHasVehicleSetsFalseByDefault() {
        var checkin = new Checkin();
        checkin.setHasVehicle(null);
        assertFalse(checkin.getHasVehicle());

        checkin.setHasVehicle(true);
        assertTrue(checkin.getHasVehicle());

        checkin.setHasVehicle(false);
        assertFalse(checkin.getHasVehicle());
    }


    @Test
    void getPersonId() {
        var checkin = new Checkin();
        assertNull(checkin.getPersonId());

        var person = new Person();
        person.setId(1L);
        checkin.setPerson(person);
        assertEquals(1L, checkin.getPersonId());
    }
}
