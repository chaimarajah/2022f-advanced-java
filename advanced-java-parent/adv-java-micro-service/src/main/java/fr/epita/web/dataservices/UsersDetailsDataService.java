package fr.epita.web.dataservices;

import fr.epita.advjava.datamodel.Address;
import fr.epita.advjava.datamodel.Country;
import fr.epita.advjava.services.AddressDAO;
import fr.epita.advjava.services.CountryDAO;
import fr.epita.web.resources.AddressDTO;
import fr.epita.web.resources.CountryDTO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.transaction.Transactional;

public class UsersDetailsDataService {
    SessionFactory sf;
    CountryDAO dao;

    AddressDAO addressDAO;

    public UsersDetailsDataService(SessionFactory sf, CountryDAO dao, AddressDAO addressDAO) {
        this.sf = sf;
        this.dao = dao;
        this.addressDAO = addressDAO;
    }

    public CountryDTO createCountry(CountryDTO dto) {
        Country country = getCountryFromDTO(dto);
        Transaction transaction = sf.openSession().beginTransaction();
        dao.create(country);
        transaction.commit();
        return dto;
    }

    private Country getCountryFromDTO(CountryDTO dto) {
        return new Country(dto.getCode(), dto.getDisplayName());
    }

    public CountryDTO getCountryDTOFromCode(String code) {
        Country predicate = new Country(code, "");
        Country foundCountry = dao.getById(predicate);
        CountryDTO countryDTO = getCountryDTO(foundCountry);
        return countryDTO;
    }

    private static CountryDTO getCountryDTO(Country foundCountry) {
        CountryDTO countryDTO = new CountryDTO();
        countryDTO.setCode(foundCountry.getShortCode());
        countryDTO.setDisplayName(foundCountry.getDisplayName());
        return countryDTO;
    }


    public AddressDTO createAddress(AddressDTO addressDTO) {
        CountryDTO countryDTO = addressDTO.getCountry();
        Country receivedCountry = new Country(countryDTO.getCode(), countryDTO.getDisplayName());
        Session session = sf.openSession();
        Transaction transaction = session.beginTransaction();
        Country country = dao.getById(receivedCountry);
        if (country == null) {
            country = getCountryFromDTO(countryDTO);
            dao.create(receivedCountry);
        }
        Address address = new Address(addressDTO.getNumber(), addressDTO.getStreet(), addressDTO.getCity(), country);
        //do a search to validate the address exists or not
        addressDAO.create(address);
        transaction.commit();
        session.close();
        addressDTO.setId(address.getId());

        return addressDTO;

    }
}
