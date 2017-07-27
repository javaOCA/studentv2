package ua.kyiv.univerpulse.studentv2.mvc.domain;

import ua.kyiv.univerpulse.studentv2.mvc.dto.AddressDto;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String city;
    private String street;
    private String home;
    private String apartment;
    private String zipcode;
    @OneToMany(mappedBy = "address")
    private List<Person> persons;

    public Address() {}

    public static class Builder {

        Address address = new Address();

        public Builder setId(AddressDto addressDto) {
            address.setId(addressDto.getId());
            return this;
        }

        public Builder setCity(AddressDto addressDto) {
            address.setCity(addressDto.getCity());
            return this;
        }

        public Builder setStreet(AddressDto addressDto) {
            address.setStreet(addressDto.getStreet());
            return this;
        }

        public Builder setHome(AddressDto addressDto) {
            address.setHome(addressDto.getHome());
            return this;
        }

        public Builder setAppartment(AddressDto addressDto) {
            address.setApartment(addressDto.getApartment());
            return this;
        }

        public Builder setZipcode(AddressDto addressDto) {
            address.setZipcode(addressDto.getZipcode());
            return this;
        }

        public Address build() { return address; }

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHome() {
        return home;
    }

    public void setHome(String home) {
        this.home = home;
    }

    public String getApartment() {
        return apartment;
    }

    public void setApartment(String apartment) {
        this.apartment = apartment;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public List<Person> getPersons() {
        return persons;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Address address = (Address) o;

        if (!city.equals(address.city)) return false;
        if (!street.equals(address.street)) return false;
        if (!home.equals(address.home)) return false;
        return apartment != null ? apartment.equals(address.apartment) : address.apartment == null;
    }

    @Override
    public int hashCode() {
        int result = city.hashCode();
        result = 31 * result + street.hashCode();
        result = 31 * result + home.hashCode();
        result = 31 * result + (apartment != null ? apartment.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", home='" + home + '\'' +
                ", apartment='" + apartment + '\'' +
                ", zipcode='" + zipcode + '\'' +
                ", persons=" + persons +
                '}';
    }
}
