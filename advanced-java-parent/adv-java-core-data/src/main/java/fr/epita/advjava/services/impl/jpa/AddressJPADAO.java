package fr.epita.advjava.services.impl.jpa;

import fr.epita.advjava.datamodel.Address;
import fr.epita.advjava.services.AddressDAO;
import org.hibernate.SessionFactory;

import java.util.List;

public class AddressJPADAO extends AbstractJPADAO<Address> implements AddressDAO {
    public AddressJPADAO(SessionFactory sf) {
        super(sf);
    }

    @Override
    public List<Address> search(Address instance) {
        return null;
    }

    @Override
    public Address getById(Address instance) {
        return null;
    }
}
