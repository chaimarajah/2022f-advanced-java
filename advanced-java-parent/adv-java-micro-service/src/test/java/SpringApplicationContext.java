import fr.epita.advjava.services.UserDAO;
import fr.epita.advjava.services.impl.jpa.UserJPADAO;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

import javax.sql.DataSource;
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
}
