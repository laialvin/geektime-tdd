package geektime.tdd.model;

import jakarta.persistence.EntityManager;

import java.util.Optional;

public class StudentRepository {
    private final EntityManager entityManager;

    public StudentRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    public Student save(Student student) {
        entityManager.persist(student);
        return student;
    }


    public Optional<Student> getById(long id) {
        return Optional.ofNullable(entityManager.find(Student.class, id));
    }
}
