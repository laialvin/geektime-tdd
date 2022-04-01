package geektime.tdd.model;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

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


    public Optional<Student> findById(long id) {
        return Optional.ofNullable(entityManager.find(Student.class, id));
    }


    public Optional<Student> findByEmail(String email) {
        TypedQuery<Student> query = entityManager.createQuery("SELECT s FROM Student s WHERE s.email = :email", Student.class);
        query.setParameter("email", email);
        return query.getResultList().stream().findFirst();
    }
}
