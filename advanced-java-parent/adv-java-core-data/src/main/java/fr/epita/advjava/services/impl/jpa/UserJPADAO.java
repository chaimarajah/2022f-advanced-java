package fr.epita.advjava.services.impl.jpa;

import fr.epita.advjava.datamodel.Country;
import fr.epita.advjava.datamodel.User;
import fr.epita.advjava.services.UserDAO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class UserJPADAO extends AbstractJPADAO<User> implements UserDAO {

    public UserJPADAO(SessionFactory sf) {
        super(sf);
    }


    @Override
    public List<User> search(User instance) {
        return null;
    }

    @Override
    public User getById(User instance) {
        return null;
    }
}
