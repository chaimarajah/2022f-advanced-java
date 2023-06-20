package fr.epita.advjava.services.impl.jpa;

import fr.epita.advjava.datamodel.Country;
import fr.epita.advjava.services.CountryDAO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class CountryJPADAO implements CountryDAO {


    private final SessionFactory sf;

    public CountryJPADAO(SessionFactory sf) {
        this.sf = sf;
    }

    @Override
    public void create(Country country) {

        Session session = sf.openSession();
        Transaction transaction = session.beginTransaction();
        session.persist(country);
        transaction.commit();
    }

    @Override
    public void update(Country country) {
        Session session = sf.openSession();
        session.update(country);

    }

    @Override
    public void delete(Country country) {
        Session session = sf.openSession();
        session.delete(country);
    }

    @Override
    public List<Country> search(Country country) {
        return null;
    }

    public Country getById(Country country){
        Session session = sf.openSession();
        Country foundCountry = session.get(Country.class, country.getShortCode());
        return foundCountry;
    }


}
