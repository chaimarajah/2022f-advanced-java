package fr.epita.advjava.services.impl.jpa;

import fr.epita.advjava.services.GenericDAO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public abstract class AbstractJPADAO<T> implements GenericDAO<T> {


    private final SessionFactory sf;

    public AbstractJPADAO(SessionFactory sf) {
        this.sf = sf;
    }


    @Override
    public void create(T instance) {
        Session session = getSession();
        session.persist(instance);
    }

    @Override
    public void update(T instance) {
        Session session = getSession();
        session.update(instance);
    }

    @Override
    public void delete(T instance) {
        Session session = getSession();
        session.delete(instance);

    }

    @Override
    public abstract List<T> search(T instance);

    @Override
    public abstract T getById(T instance);


    public Session getSession(){
        Session currentSession = this.sf.getCurrentSession();
        if (currentSession == null){
            currentSession = this.sf.openSession();
        }
        return currentSession;

    }
}
