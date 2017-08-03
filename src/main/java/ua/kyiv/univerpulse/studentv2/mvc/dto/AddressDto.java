package ua.kyiv.univerpulse.studentv2.mvc.dto;

import ua.kyiv.univerpulse.studentv2.mvc.domain.Address;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class AddressDto {
    private Long id;
    @NotNull
    @Pattern(regexp = "\\w{2,}", message = "{message.city.err}")
    private String city;
    @NotNull
    @Size(min = 2, message = "{message.street.err}")
    private String street;
    @NotNull
    @Pattern(regexp = "^[1-9]{1}\\w*$", message = "{message.home.err}")
    private String home;
    private String apartment;
    @Pattern(regexp = "\\d{5}", message = "{message.zip.err}")
    private String zipcode;

    public AddressDto() {}

    public static class Builder {

        AddressDto addressDto = new AddressDto();

        public Builder setId(Address address) {
            addressDto.setId(address.getId());
            return this;
        }

        public Builder setCity(Address address) {
            addressDto.setCity(address.getCity());
            return this;
        }

        public Builder setStreet(Address address) {
            addressDto.setStreet(address.getStreet());
            return this;
        }

        public Builder setHome(Address address) {
            addressDto.setHome(address.getHome());
            return this;
        }

        public Builder setAppartment(Address address) {
            addressDto.setApartment(address.getApartment());
            return this;
        }

        public Builder setZipcode(Address address) {
            addressDto.setZipcode(address.getZipcode());
            return this;
        }

        public AddressDto build() { return addressDto; }

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

    @Override
    public String toString() {
        return "AddressDto{" +
                "id=" + id +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", home='" + home + '\'' +
                ", apartment='" + apartment + '\'' +
                ", zipcode='" + zipcode + '\'' +
                '}';
    }
}
