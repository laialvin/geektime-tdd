package geektime.tdd;

import geektime.tdd.repository.StudentRepository;
import geektime.tdd.repository.StudentRepositoryFeature;
import org.glassfish.jersey.logging.LoggingFeature;
import org.glassfish.jersey.server.ResourceConfig;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Application extends ResourceConfig {
    public Application() {
//        register(StudentResource.class);
        packages("geektime.tdd.resources");
        register(StudentRepositoryFeature.class);
        register(new LoggingFeature(Logger.getLogger(LoggingFeature.DEFAULT_LOGGER_NAME),
                Level.INFO, LoggingFeature.Verbosity.PAYLOAD_ANY, 10000));
    }
}
