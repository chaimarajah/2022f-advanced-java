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
import java.net.InetSocketAddress;
import java.net.URI;

public class TestHttpService {


    public static void main(String[] args) throws IOException {

        testHttpServer();
    }
    public static void testHttpServer() throws IOException {

        ApplicationContext context = new AnnotationConfigApplicationContext(SpringApplicationContext.class);
        UserDAO userDAO = context.getBean(UserDAO.class);
        CountryDAO countryDAO = context.getBean(CountryDAO.class);

        HttpServer server = HttpServer.create(new InetSocketAddress("0.0.0.0",80), 0);
        server.createContext("/", httpRequest -> {

            String requestMethod = httpRequest.getRequestMethod();
            if ("POST".equalsIgnoreCase(requestMethod)){
                ObjectMapper mapper = new ObjectMapper();
                CountryDTO countryDTO = mapper.readValue(httpRequest.getRequestBody(), CountryDTO.class);
                //some business logic
                Country country = new Country(countryDTO.getCode(), countryDTO.getDisplayName());
                countryDAO.create(country);
                httpRequest.sendResponseHeaders(201, "created".length());
                httpRequest.getResponseBody().write(country.getShortCode().getBytes());
                httpRequest.getResponseBody().close();
                httpRequest.close();
                return;
            }
            //get for the next time
            byte[] requestBytes = httpRequest.getRequestBody().readAllBytes();
            System.out.println( new String(requestBytes));

            URI requestURI = httpRequest.getRequestURI();
            //parse request
            System.out.println(requestURI);
            String ok = "ok";
            httpRequest.sendResponseHeaders(200, ok.length());

            httpRequest.close();

        });
        server.start();

    }



}

