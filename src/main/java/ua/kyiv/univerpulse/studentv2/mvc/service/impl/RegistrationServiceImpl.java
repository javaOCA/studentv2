package ua.kyiv.univerpulse.studentv2.mvc.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ua.kyiv.univerpulse.studentv2.mvc.domain.*;
import ua.kyiv.univerpulse.studentv2.mvc.dto.AddressDto;
import ua.kyiv.univerpulse.studentv2.mvc.dto.MarksDto;
import ua.kyiv.univerpulse.studentv2.mvc.dto.PersonDto;
import ua.kyiv.univerpulse.studentv2.mvc.exception.PersonMailException;
import ua.kyiv.univerpulse.studentv2.mvc.exception.PersonSaveException;
import ua.kyiv.univerpulse.studentv2.mvc.exception.ServiceException;
import ua.kyiv.univerpulse.studentv2.mvc.repository.PersonRepository;
import ua.kyiv.univerpulse.studentv2.mvc.repository.RoleRepository;
import ua.kyiv.univerpulse.studentv2.mvc.service.MailService;
import ua.kyiv.univerpulse.studentv2.mvc.service.RegistrationService;

import java.util.Objects;

@Service
public class RegistrationServiceImpl implements RegistrationService {

    private final static Logger logger = Logger.getLogger(RegistrationServiceImpl.class);

    private PersonRepository personRepository;
    private RoleRepository roleRepository;
    private MailService mailService;
    @Autowired
    public RegistrationServiceImpl(PersonRepository personRepository, RoleRepository roleRepository, MailService mailService) {
        this.personRepository = personRepository;
        this.roleRepository = roleRepository;
        this.mailService = mailService;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
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
            if (logger.isDebugEnabled())
                logger.debug("Save in DB person with login: " + person.getLogin());
            mailService.sendMessage(personDto);
            if (logger.isDebugEnabled())
                logger.debug("Send e-mail to " + person.getEmail());
        } catch (PersonSaveException | PersonMailException e){
            logger.error("Catch person save or mail exception", e);
//            throw new ServiceException(e.getMessage());
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

    @Override
    public Person findPersonByNameAndAddress(PersonDto personDto, AddressDto addressDto) {
        Person person = new Person();
        try {
            person = personRepository.findPersonByNameAndAddress(personDto.getFirstName(), personDto.getLastName(),
                    addressDto.getCity(), addressDto.getStreet(), addressDto.getHome(), addressDto.getApartment());
        } catch (PersonSaveException e) {
            throw new ServiceException(e.getMessage());
        }
        return person;
    }
}
