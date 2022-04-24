package geektime.tdd;

import geektime.tdd.model.Student;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.Application;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.jupiter.api.*;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ApiTest extends JerseyTest {
    Student[] students = new Student[] {
            new Student("Alvin", "Lai", "alvinlai@test.cn"),
            new Student("John", "Snow", "john.snow@test.cn"),
            new Student("Peter", "Park", "peter.park@test.cn"),
    };

    @Override
    protected Application configure() {
        return new geektime.tdd.Application();
    }


    @Test
    @Order(1)
    public void should_save_student() {
        Stream.of(students)
                .map(student -> Entity.entity(student, MediaType.APPLICATION_JSON_TYPE))
                .forEach(entity -> target("students/save").request().post(entity));
    }


    @Test
    public void should_be_able_to_fetch_all_students() {
        Student[] saved = target("students").request().get(Student[].class);
        assertEquals(students.length, saved.length);

        Student alvin = saved[0];
        assertEquals(1, alvin.getId());
        assertEquals("Alvin", alvin.getFirstName());
        assertEquals("Lai", alvin.getLastName());
        assertEquals("alvinlai@test.cn", alvin.getEmail());
    }


    @Test
    public void should_be_able_to_fetch_student_by_id() {
        Student alvin = target("students/1").request().get(Student.class);
        assertEquals(1, alvin.getId());
        assertEquals("Alvin", alvin.getFirstName());
        assertEquals("Lai", alvin.getLastName());
        assertEquals("alvinlai@test.cn", alvin.getEmail());

        Student john = target("students/2").request().get(Student.class);
        assertEquals(2, john.getId());
        assertEquals("John", john.getFirstName());
        assertEquals("Snow", john.getLastName());
        assertEquals("john.snow@test.cn", john.getEmail());

        Student peter = target("students/3").request().get(Student.class);
        assertEquals(3, peter.getId());
        assertEquals("Peter", peter.getFirstName());
        assertEquals("Park", peter.getLastName());
        assertEquals("peter.park@test.cn", peter.getEmail());
    }


    @Test
    @Order(3)
    public void should_return_404_if_student_not_found() {
        Response response = target("students/5").request().get(Response.class);
        assertEquals(Response.Status.NOT_FOUND.getStatusCode(), response.getStatus());
    }

}
