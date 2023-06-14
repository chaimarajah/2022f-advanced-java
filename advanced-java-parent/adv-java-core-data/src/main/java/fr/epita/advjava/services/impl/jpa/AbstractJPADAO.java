package fr.epita.advjava.services.impl.jpa;

import fr.epita.advjava.datamodel.Country;
import fr.epita.advjava.services.GenericDAO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public abstract class AbstractJPADAO<T> implements GenericDAO<T> {


    private final SessionFactory sf;

    public AbstractJPADAO(SessionFactory sf) {
        this.sf = sf;
    }


    @Override
    public void create(T instance) {

        Session session = sf.openSession();
        Transaction transaction = session.beginTransaction();
        session.persist(instance);
        transaction.commit();
    }

    @Override
    public void update(T instance) {
        Session session = sf.openSession();
        session.update(instance);
    }

    @Override
    public void delete(T instance) {
        Session session = sf.openSession();
        session.delete(instance);

    }

    @Override
    public abstract List<T> search(T instance);

    @Override
    public abstract T getById(T instance);
}
