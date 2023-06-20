import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpServer;
import fr.epita.advjava.datamodel.Country;
import fr.epita.advjava.services.CountryDAO;
import fr.epita.advjava.services.UserDAO;
import fr.epita.web.resources.CountryDTO;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;
import java.net.HttpCookie;
import java.net.InetSocketAddress;
import java.net.URI;

public class TestHttpService {


    public static void main(String[] args) throws IOException {
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringApplicationContext.class);
        HttpServer server = context.getBean(HttpServer.class);
        server.start();

    }
    public static void testHttpServer() throws IOException {



    }



}

