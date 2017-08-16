package ua.kyiv.univerpulse.studentv2.mvc.service.impl;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.kyiv.univerpulse.studentv2.mvc.domain.Person;
import ua.kyiv.univerpulse.studentv2.mvc.dto.PersonDto;
import ua.kyiv.univerpulse.studentv2.mvc.repository.PersonRepository;
import ua.kyiv.univerpulse.studentv2.mvc.service.LoginService;

import java.util.Objects;

@Service
public class LoginServiceImpl implements LoginService {

    private final static Logger logger = Logger.getLogger(RegistrationServiceImpl.class);

    private PersonRepository personRepository;
    @Autowired
    public LoginServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public PersonDto verifyLogin(String login, String password) {
        Person person = personRepository.findPersonByLogin(login);
        PersonDto personDto;
        if (Objects.nonNull(person)) {
            switch (person.getRole().getRole()) {
                case ROLE_ADMIN:
                    personDto = new PersonDto.Builder()
                            .setId(person).setLogin(person).setPassword(person).setRole(person).build();
                    break;
                default:
                    personDto = new PersonDto.Builder().setId(person).setLogin(person).setPassword(person)
                            .setFirstName(person).setLastName(person).setBirthday(person).setEducation(person)
                            .setEmail(person).setPhone(person).setRole(person).build();
            }
            if (Objects.nonNull(personDto) && personDto.getPassword().equals(DigestUtils.md5Hex(password + login))) {
                if (logger.isDebugEnabled())
                    logger.debug("Connect person with login = " + login);
                return personDto;
            }
        }
        return null;
    }
}
