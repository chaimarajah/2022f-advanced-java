import fr.epita.advjava.datamodel.Address;
import fr.epita.advjava.datamodel.Country;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.inject.Inject;
import java.util.List;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = SpringApplicationContext.class)
public class AnotherHibernateTestCase {


    @Inject
    SessionFactory sf;


    @Test
    public void testSth(){
        Session session = sf.openSession();

        String epitaCity = "Le Kremlin Bicêtre";
        Country country = new Country("FRA", "FRANCE");
        Address address = new Address("14", "rue voltaire", epitaCity, country);

        Transaction transaction = session.beginTransaction();
        session.persist(country);
        session.persist(address);
        transaction.commit();
        Query query = session.createQuery("from Address a where a.city = :city");
        query.setParameter("city", epitaCity);

        List list = query.list();
        Assertions.assertEquals(1,list.size());

    }
}
