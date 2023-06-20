package fr.epita.web.app;


import fr.epita.advjava.services.CountryDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestRestControllers {


    @Autowired
    CountryDAO dao;


    @GetMapping(path = "/someContent")
    public ResponseEntity<String> firstGetMethod(){
        return ResponseEntity.ok("test sucessful");
    }

}
