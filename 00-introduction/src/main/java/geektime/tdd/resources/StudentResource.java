package geektime.tdd.resources;


import geektime.tdd.model.Student;
import geektime.tdd.repository.StudentRepository;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/students")
public class StudentResource {

    private final StudentRepository studentRepository;


    @Inject
    public StudentResource(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Student> list() {
        return studentRepository.list();
    }

    @GET
    @Path("{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUser(@PathParam("userId") long userId) {
        return studentRepository.findById(userId).map(Response::ok)
                .orElse(Response.status(Response.Status.NOT_FOUND)).build();
    }


    @POST
    @Path("save")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public long save(Student student) {
        Student saved = studentRepository.save(student);
        return saved.getId();
    }
}
