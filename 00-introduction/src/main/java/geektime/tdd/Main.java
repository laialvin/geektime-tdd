package geektime.tdd;

import jakarta.ws.rs.core.UriBuilder;
import org.eclipse.jetty.server.Server;
import org.glassfish.jersey.jetty.JettyHttpContainerFactory;

public class Main {
    public static void main(String[] args) throws Exception {
        Server server = JettyHttpContainerFactory
                .createServer(UriBuilder.fromUri("http://localhost/").port(8051).build(), new Application());
        server.start();
        System.out.println("server started!");
        server.join();
    }
}
