package geektime.tdd.model;


import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class TestStudentRepository {
    private EntityManagerFactory factory;
    private EntityManager entityManager;
    private StudentRepository repository;
    private Student student;

    @BeforeEach
    void before() {
        factory = Persistence.createEntityManagerFactory("student");
        entityManager = factory.createEntityManager();
        repository = new StudentRepository(entityManager);
        entityManager.getTransaction().begin();
        student = new Student("alvin", "lai", "alvinlai@live.cn");
        repository.save(student);
        entityManager.getTransaction().commit();
    }


    @AfterEach
    void after() {
        entityManager.clear();
        entityManager.close();
        factory.close();
    }


    @Test
    void should_generate_id_for_saved_entity() {
        assertNotEquals(0, student.getId());
    }


    @Test
    void should_be_able_to_load_saved_student_by_id() {
        Optional<Student> optional = repository.findById(student.getId());
        assertTrue(optional.isPresent());
        Student saved = optional.get();
        assertEquals(student, saved);
    }


    @Test
    void should_be_able_to_load_saved_student_by_email() {
        Optional<Student> optional = repository.findByEmail(student.getEmail());
        assertTrue(optional.isPresent());
        Student saved = optional.get();
        assertEquals(student, saved);
    }
}
