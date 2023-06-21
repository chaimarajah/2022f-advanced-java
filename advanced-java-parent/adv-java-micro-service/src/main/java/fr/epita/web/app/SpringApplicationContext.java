package fr.epita.web.app;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpServer;
import fr.epita.advjava.datamodel.Country;
import fr.epita.advjava.services.CountryDAO;
import fr.epita.advjava.services.UserDAO;
import fr.epita.advjava.services.impl.jpa.CountryJPADAO;
import fr.epita.advjava.services.impl.jpa.UserJPADAO;
import fr.epita.web.dataservices.UsersDetailsDataService;
import fr.epita.web.resources.CountryDTO;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

import javax.sql.DataSource;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.URI;
import java.util.Properties;

@Configuration
public class SpringApplicationContext {

    @Bean
    public DataSource mainDatasource(){
        return new DriverManagerDataSource("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1", "user","user");
    }

    @Bean
    public LocalSessionFactoryBean sessionFactoryBean(DataSource ds){
        LocalSessionFactoryBean localSessionFactoryBean = new LocalSessionFactoryBean();
        localSessionFactoryBean.setDataSource(ds);
        localSessionFactoryBean.setPackagesToScan("fr.epita.advjava.datamodel");
        Properties properties = new Properties();
        properties.put("hibernate.hbm2ddl.auto", "create");
        properties.put("hibernate.show_sql","true");
        properties.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");

        localSessionFactoryBean.setHibernateProperties(properties);
        return localSessionFactoryBean;
    }


    @Bean
    public UserDAO getUserDAO(SessionFactory sf) {
        return new UserJPADAO(sf);
    }
    @Bean("dao.jpa.countryDAO")
    public CountryDAO getCountryDAO(SessionFactory sf) {
        return new CountryJPADAO(sf);
    }


    @Bean
    public HttpServer getServer(@Qualifier("dao.jpa.countryDAO") CountryDAO countryDAO) throws IOException {

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
        return server;
    }

    @Bean
    public UsersDetailsDataService usersDetailsDataService(CountryDAO dao, SessionFactory sf){
        return new UsersDetailsDataService(sf, dao);
    }
}
