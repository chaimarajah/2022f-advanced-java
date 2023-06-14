package fr.epita.advjava.datamodel;


import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class Country implements Serializable {

    @Id
    String shortCode;

    String displayName;


    public Country(){

    }
    public Country(String shortCode, String displayName) {
        this.shortCode = shortCode;
        this.displayName = displayName;
    }

    public String getShortCode() {
        return shortCode;
    }

    public void setShortCode(String shortCode) {
        this.shortCode = shortCode;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
}
