package fr.epita.web.dataservices;

import fr.epita.advjava.datamodel.Country;
import fr.epita.advjava.services.CountryDAO;
import fr.epita.web.resources.CountryDTO;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class UsersDetailsDataService {
    SessionFactory sf;
    CountryDAO dao;

    public UsersDetailsDataService(SessionFactory sf, CountryDAO dao) {
        this.sf = sf;
        this.dao = dao;
    }

    public CountryDTO createCountry(CountryDTO dto){
        Country country = new Country(dto.getCode(), dto.getDisplayName());
        Transaction transaction = sf.openSession().beginTransaction();
        dao.create(country);
        transaction.commit();
        return dto;
    }

    public CountryDTO getCountry(String code){
        Country predicate = new Country(code, "");
        Country foundCountry = dao.getById(predicate);
        CountryDTO countryDTO = new CountryDTO();
        countryDTO.setCode(foundCountry.getShortCode());
        countryDTO.setDisplayName(foundCountry.getDisplayName());
        return countryDTO;
    }




}
