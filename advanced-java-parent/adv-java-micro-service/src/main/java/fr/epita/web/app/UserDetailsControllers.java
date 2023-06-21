package fr.epita.web.app;



import fr.epita.advjava.services.CountryDAO;
import fr.epita.web.dataservices.UsersDetailsDataService;
import fr.epita.web.resources.CountryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
public class UserDetailsControllers {


    @Autowired
    CountryDAO dao;

    @Autowired
    UsersDetailsDataService ds;


    @GetMapping(path = "/someContent")
    public ResponseEntity<String> firstGetMethod(){
        return ResponseEntity.ok("test sucessful");
    }

    @PostMapping(value = "/countries",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<String> firstPostMethod(@RequestBody CountryDTO dto){
        CountryDTO country = ds.createCountry(dto);
        return ResponseEntity
                .created(URI.create("/countries/" + country.getCode()))
                .body("created the country sucessfully");
    }


    @GetMapping(value = "/countries/{code}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<CountryDTO> getCountryByCode(@PathVariable("code") String code){
        CountryDTO country = ds.getCountry(code);
        return ResponseEntity.ok().body(country);
    }
}
