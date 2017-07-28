package ua.kyiv.univerpulse.studentv2.mvc.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.kyiv.univerpulse.studentv2.mvc.domain.*;
import ua.kyiv.univerpulse.studentv2.mvc.dto.AddressDto;
import ua.kyiv.univerpulse.studentv2.mvc.dto.MarksDto;
import ua.kyiv.univerpulse.studentv2.mvc.dto.PersonDto;
import ua.kyiv.univerpulse.studentv2.mvc.exception.PersonSaveException;
import ua.kyiv.univerpulse.studentv2.mvc.exception.ServiceException;
import ua.kyiv.univerpulse.studentv2.mvc.repository.PersonRepository;
import ua.kyiv.univerpulse.studentv2.mvc.repository.RoleRepository;
import ua.kyiv.univerpulse.studentv2.mvc.service.RegistrationService;

import java.util.Objects;

@Service
public class RegistrationServiceImpl implements RegistrationService {

    private PersonRepository personRepository;
    private RoleRepository roleRepository;
    @Autowired
    public RegistrationServiceImpl(PersonRepository personRepository, RoleRepository roleRepository) {
        this.personRepository = personRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public void savePerson(PersonDto personDto, AddressDto addressDto, MarksDto marksDto) {
        Role role = roleRepository.findRoleByRoleName(RoleEnum.USER);
        Address address = new Address.Builder()
                .setCity(addressDto).setStreet(addressDto)
                .setHome(addressDto).setAppartment(addressDto)
                .setZipcode(addressDto).build();
        Marks marks = new Marks.Builder()
                .setAlgebra(marksDto).setGeometry(marksDto)
                .setPhysics(marksDto).setChemistry(marksDto)
                .setBiology(marksDto).setComputer_science(marksDto)
                .setEnglish(marksDto).setGeography(marksDto)
                .setLiterature(marksDto).setMusic(marksDto)
                .setDrawing(marksDto).build();
        Person person = new Person.Builder()
                .setLogin(personDto).setPassword(personDto)
                .setFirstName(personDto).setLastName(personDto)
                .setBirthday(personDto).setEducation(personDto)
                .setEmail(personDto).setPhone(personDto)
                .setAddress(address).setMarks(marks)
                .setRole(role).build();
        try {
            personRepository.savePerson(person);
        }catch (PersonSaveException e){
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public boolean findPersonByLogin(PersonDto personDto) {
        String login = personDto.getLogin();
        Person person = personRepository.findPersonByLogin(login);
        if (Objects.isNull(person)) {
            return false;
        } else {
            return true;
        }
    }
}
