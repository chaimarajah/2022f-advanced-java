import fr.epita.advjava.UsersDAO;
import fr.epita.advjava.datamodel.User;
import fr.epita.advjava.services.exceptions.DatamodelCreationException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.inject.Inject;

import javax.inject.Named;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = SpringApplicationContext.class)
public class TestSpring {

    @Inject
    @Named("testDI")
    String helloWorld;

    @Inject
    UsersDAO dao;

    @Test
    public void test() throws DatamodelCreationException {
        System.out.println(helloWorld);
        User user = new User();
        user.setId(0);
        user.setName("Test");
        dao.create(user);
    }


}
