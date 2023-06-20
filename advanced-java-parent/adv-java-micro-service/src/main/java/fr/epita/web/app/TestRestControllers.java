package fr.epita.web.app;



import fr.epita.advjava.datamodel.Country;
import fr.epita.advjava.services.CountryDAO;
import fr.epita.web.resources.CountryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
public class TestRestControllers {


    @Autowired
    CountryDAO dao;

    @GetMapping(path = "/someContent")
    public ResponseEntity<String> firstGetMethod(){
        return ResponseEntity.ok("test sucessful");
    }

//    @PostMapping(value = "/countries",
//            consumes = MediaType.APPLICATION_JSON_VALUE,
//            produces = MediaType.APPLICATION_JSON_VALUE
//    )
//    public ResponseEntity<String> firstPostMethod(@RequestBody CountryDTO dto){
//        Country country = new Country(dto.getCode(), dto.getDisplayName());
//        dao.create(country);
//        return ResponseEntity
//                .created(URI.create("/countries/" + country.getShortCode()))
//                .body("created the country sucessfully");
//    }


    @GetMapping(value = "/countries/{code}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<CountryDTO> getCountryByCode(@PathVariable("code") String code){
        Country predicate = new Country(code, "");
        Country foundCountry = dao.getById(predicate);
        CountryDTO countryDTO = new CountryDTO();
        countryDTO.setCode(foundCountry.getShortCode());
        countryDTO.setDisplayName(foundCountry.getDisplayName());
        return ResponseEntity.ok().body(countryDTO);
    }
}
