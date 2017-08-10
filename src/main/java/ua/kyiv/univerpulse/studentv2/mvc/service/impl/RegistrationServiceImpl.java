package ua.kyiv.univerpulse.studentv2.mvc.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ua.kyiv.univerpulse.studentv2.mvc.domain.*;
import ua.kyiv.univerpulse.studentv2.mvc.dto.AddressDto;
import ua.kyiv.univerpulse.studentv2.mvc.dto.FacultyDto;
import ua.kyiv.univerpulse.studentv2.mvc.dto.MarksDto;
import ua.kyiv.univerpulse.studentv2.mvc.dto.PersonDto;
import ua.kyiv.univerpulse.studentv2.mvc.exception.PersonMailException;
import ua.kyiv.univerpulse.studentv2.mvc.exception.PersonSaveException;
import ua.kyiv.univerpulse.studentv2.mvc.exception.ServiceException;
import ua.kyiv.univerpulse.studentv2.mvc.exception.UploadFileException;
import ua.kyiv.univerpulse.studentv2.mvc.repository.FacultyRepository;
import ua.kyiv.univerpulse.studentv2.mvc.repository.PersonRepository;
import ua.kyiv.univerpulse.studentv2.mvc.repository.RoleRepository;
import ua.kyiv.univerpulse.studentv2.mvc.service.MailService;
import ua.kyiv.univerpulse.studentv2.mvc.service.RegistrationService;
import ua.kyiv.univerpulse.studentv2.mvc.service.ResultService;
import ua.kyiv.univerpulse.studentv2.mvc.service.UploadFiles;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class RegistrationServiceImpl implements RegistrationService {

    private final static Logger logger = Logger.getLogger(RegistrationServiceImpl.class);

    private PersonRepository personRepository;
    private RoleRepository roleRepository;
    private MailService mailService;
    private UploadFiles uploadFiles;
    private FacultyRepository facultyRepository;
    private ResultService resultService;
    @Autowired
    public RegistrationServiceImpl(PersonRepository personRepository, RoleRepository roleRepository,
                                   MailService mailService, UploadFiles uploadFiles,
                                   FacultyRepository facultyRepository, ResultService resultService) {
        this.personRepository = personRepository;
        this.roleRepository = roleRepository;
        this.mailService = mailService;
        this.uploadFiles = uploadFiles;
        this.facultyRepository = facultyRepository;
        this.resultService = resultService;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public List<FileInfo> savePerson(PersonDto personDto, AddressDto addressDto, MarksDto marksDto, MultipartFile[] files) {
        Role role = roleRepository.findRoleByRoleName(RoleEnum.ROLE_USER);
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
        Faculty faculty = new Faculty();
        Enlist enlist = new Enlist();
        enlist.setTotalScore(resultService.getTotalResult(marksDto));
        enlist.setAction(ActionEnum.PROCESS);
        person.setFaculty(facultyRepository.findFacultyByName(personDto.getFaculty()));
        person.setEnlist(enlist);
        List<FileInfo> uploadedFiles = new ArrayList<>();
        try {
            personRepository.savePerson(person);
            if (logger.isDebugEnabled())
                logger.debug("Save in DB person with id: " + person.getId());
//            mailService.sendMessage(personDto);
            if (logger.isDebugEnabled())
                logger.debug("Send e-mail to " + person.getEmail());
            uploadedFiles = uploadFiles.uploadFiles(files, person);
            if (logger.isDebugEnabled())
                logger.debug("Save person files to disk");
        } catch (PersonSaveException | PersonMailException | UploadFileException e){
            logger.error("Catch person save or mail exception", e);
            throw new ServiceException(e.getMessage());
        }
        return uploadedFiles;
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

    @Override
    public List<Faculty> getAllFaculty() {
        return facultyRepository.getAllFaculty();
    }

    @Override
    public void updateFaculty(FacultyDto facultyDto) {
        Faculty faculty = new Faculty.Builder().getId(facultyDto)
                .getName(facultyDto).getEvaluationDate(facultyDto)
                .getPassingScore(facultyDto).getNumberOfStudents(facultyDto).build();
        if(logger.isDebugEnabled())
            logger.debug("Update entity Faculty in RegistrationServiceImpl: " + faculty);
        facultyRepository.updateFaculty(faculty);
    }

    @Override
    public void saveFaculty(FacultyDto facultyDto) {
        Faculty faculty = new Faculty.Builder().getName(facultyDto)
                .getEvaluationDate(facultyDto).getPassingScore(facultyDto)
                .getNumberOfStudents(facultyDto).build();
        facultyRepository.saveFaculty(faculty);
    }

    @Override
    public boolean findEvaluationDateByFaculty(PersonDto personDto) {
        LocalDate registeredDate = LocalDate.now();
        LocalDate evaluationDate = (facultyRepository.getEvaluationDateByName(personDto.getFaculty())).toLocalDate();
        if (logger.isDebugEnabled())
            logger.debug("registeredDate = " + registeredDate + "; evaluationDate = " + evaluationDate);
        if(registeredDate.isAfter(evaluationDate)) {
            return true;
        } else {
            return false;
        }
    }
}
