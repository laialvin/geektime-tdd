package geektime.tdd.model;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.Optional;

public class Main {

    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("student");
        EntityManager entityManager = factory.createEntityManager();
        entityManager.getTransaction().begin();
        Student alvin = new Student("alvin", "lai", "alvinlai@live.cn");
        StudentRepository studentRepository = new StudentRepository(entityManager);
        alvin = studentRepository.save(alvin);
        entityManager.getTransaction().commit();
        System.out.println(alvin.getId());

        Optional<Student> loaded = studentRepository.getById(alvin.getId());
        System.out.println(loaded);
    }
}
