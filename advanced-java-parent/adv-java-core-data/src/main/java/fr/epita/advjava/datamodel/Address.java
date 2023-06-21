package fr.epita.advjava.datamodel;


import javax.persistence.*;

@Entity
public class Address {


    public Address(){

    }
    public Address(String number, String street, String city, Country country) {
        this.number = number;
        this.street = street;
        this.city = city;
        this.country = country;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;

    String number;
    String street;
    String city;

    @ManyToOne
    @JoinColumn(name = "ref_country")
    Country country;


    public Integer getId() {
        return id;
    }

    public String getNumber() {
        return number;
    }

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public Country getCountry() {
        return country;
    }
}
