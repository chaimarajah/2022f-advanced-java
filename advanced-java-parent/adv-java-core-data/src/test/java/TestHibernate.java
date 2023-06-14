import fr.epita.advjava.datamodel.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.inject.Inject;
import java.util.List;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = SpringApplicationContext.class)
public class TestHibernate {


    @Inject
    SessionFactory sessionFactory;

    @Test
    public void test() {
        Session session = sessionFactory.openSession();
        User user = new User();
        user.setName("test");
        Transaction transaction = session.beginTransaction();
        session.persist(user);
        session.flush();
        transaction.commit();

        List<User> list = session.createQuery("from User u where u.name = 'test'", User.class).list();

        Assertions.assertEquals(list.get(0).getName(), "test");
    }
}
