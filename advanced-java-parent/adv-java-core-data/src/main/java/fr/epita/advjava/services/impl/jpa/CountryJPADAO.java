package fr.epita.advjava.services.impl.jpa;

import fr.epita.advjava.datamodel.Country;
import fr.epita.advjava.services.CountryDAO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class CountryJPADAO extends AbstractJPADAO<Country> implements CountryDAO {


    public CountryJPADAO(SessionFactory sf) {
       super(sf);
    }




    @Override
    public List<Country> search(Country country) {
        return null;
    }

    public Country getById(Country country){
        Session session = getSession();
        Country foundCountry = session.get(Country.class, country.getShortCode());
        return foundCountry;
    }


}
