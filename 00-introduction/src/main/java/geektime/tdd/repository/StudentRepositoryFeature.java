package geektime.tdd.repository;

import jakarta.inject.Singleton;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import jakarta.ws.rs.core.Feature;
import jakarta.ws.rs.core.FeatureContext;
import org.glassfish.jersey.internal.inject.AbstractBinder;

public class StudentRepositoryFeature implements Feature {

    @Override
    public boolean configure(FeatureContext context) {
        context.register(new AbstractBinder() {
            @Override
            protected void configure() {
                bindAsContract(StudentRepository.class).in(Singleton.class);
                bindFactory(() -> Persistence.createEntityManagerFactory("student").createEntityManager())
                        .to(EntityManager.class);
            }
        });
        return true;
    }

}
