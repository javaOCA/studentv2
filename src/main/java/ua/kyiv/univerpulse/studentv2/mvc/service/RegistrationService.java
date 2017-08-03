package ua.kyiv.univerpulse.studentv2.mvc.service;

import ua.kyiv.univerpulse.studentv2.mvc.domain.Person;
import ua.kyiv.univerpulse.studentv2.mvc.dto.AddressDto;
import ua.kyiv.univerpulse.studentv2.mvc.dto.MarksDto;
import ua.kyiv.univerpulse.studentv2.mvc.dto.PersonDto;

public interface RegistrationService {

    void savePerson(PersonDto personDto, AddressDto addressDto, MarksDto marksDto);

    boolean findPersonByLogin(PersonDto personDto);

    Person findPersonByNameAndAddress(PersonDto personDto, AddressDto addressDto);

}
