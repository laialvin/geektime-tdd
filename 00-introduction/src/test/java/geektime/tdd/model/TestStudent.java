package geektime.tdd.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class TestStudent {
    private final Student johnSmith = new Student("john", "smith", "john.smith@foo.com");

    @Test
    void equals_of_two_students() {
        assertEquals(johnSmith, new Student("john", "smith", "john.smith@foo.com"));
    }


    @Test
    void not_equals_of_two_students_with_different_ids() {
        Student anotherJohnSmith = new Student("john", "smith", "john.smith@foo.com");
        anotherJohnSmith.setId(1);
        assertNotEquals(johnSmith, anotherJohnSmith);
    }

    @Test
    void not_equals_of_two_students_with_different_firstNames() {
        assertNotEquals(johnSmith, new Student("william", "smith", "john.smith@foo.com"));
    }


    @Test
    void not_equals_of_two_students_with_different_lastNames() {
        assertNotEquals(johnSmith, new Student("john", "briant", "john.smith@foo.com"));
    }


    @Test
    void not_equals_of_two_students_with_different_emails() {
        assertNotEquals(johnSmith, new Student("john", "smith", "john.smith2@foo.com"));
    }
}
