package ua.kyiv.univerpulse.studentv2.mvc.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ua.kyiv.univerpulse.studentv2.mvc.domain.ActionEnum;
import ua.kyiv.univerpulse.studentv2.mvc.domain.Faculty;
import ua.kyiv.univerpulse.studentv2.mvc.domain.Marks;
import ua.kyiv.univerpulse.studentv2.mvc.domain.Person;
import ua.kyiv.univerpulse.studentv2.mvc.dto.MarksDto;
import ua.kyiv.univerpulse.studentv2.mvc.dto.PersonDto;
import ua.kyiv.univerpulse.studentv2.mvc.exception.PersonMailException;
import ua.kyiv.univerpulse.studentv2.mvc.exception.PersonSaveException;
import ua.kyiv.univerpulse.studentv2.mvc.exception.ServiceException;
import ua.kyiv.univerpulse.studentv2.mvc.repository.FacultyRepository;
import ua.kyiv.univerpulse.studentv2.mvc.repository.MarksRepository;
import ua.kyiv.univerpulse.studentv2.mvc.repository.PersonRepository;
import ua.kyiv.univerpulse.studentv2.mvc.service.ResultService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ResultServiceImpl implements ResultService {

    private static final Logger logger = Logger.getLogger(ResultServiceImpl.class);

    private MarksRepository marksRepository;
    private PersonRepository personRepository;
    private FacultyRepository facultyRepository;
    @Autowired
    public ResultServiceImpl(MarksRepository marksRepository, PersonRepository personRepository,
                             FacultyRepository facultyRepository) {
        this.marksRepository = marksRepository;
        this.personRepository = personRepository;
        this.facultyRepository = facultyRepository;
    }

    @Override
    public Integer getTotalResult(MarksDto marksDto) {
        return marksDto.getAlgebra() + marksDto.getBiology() + marksDto.getChemistry()
                + marksDto.getComputer_science() + marksDto.getDrawing() + marksDto.getEnglish()
                + marksDto.getGeography() + marksDto.getGeometry() + marksDto.getLiterature()
                + marksDto.getMusic() + marksDto.getPhysics();
    }

    @Override
    public Integer getTotalScoring(PersonDto personDto) {
        if (logger.isDebugEnabled())
            logger.debug("In ResultService person.id = " + personDto.getId());
        Marks marks = marksRepository.findMarksById(personDto.getId());
        if (Objects.nonNull(marks)) {
            MarksDto marksDto = new MarksDto.Builder().setAlgebry(marks).setBiology(marks)
                    .setChemistry(marks).setComputer_science(marks).setDrawing(marks).setEnglish(marks)
                    .setGeography(marks).setGeometry(marks).setLiterature(marks).setMusic(marks)
                    .setPhysics(marks).build();
            return getTotalResult(marksDto);
        }
        return 0;
    }

    @Override
    @Transactional(readOnly = true)
    public List<PersonDto> getAllPersonWithoutAdminWithEnlist() {
        List<Person> persons = personRepository.getAllPersonWithoutAdminWithEnlist();
        if (logger.isDebugEnabled())
            logger.debug("All persons without admins: " + persons);
        if(persons.isEmpty()) {
            if (logger.isDebugEnabled())
                logger.debug("List persons is empty");
            return new ArrayList<>();
        } else {
            return persons.stream()
                    .map(p -> new PersonDto.Builder().setId(p)
                            .setFirstName(p).setLastName(p)
                            .setEmail(p).setPhone(p)
                            .setFaculty(p).setEnlist(p).build())
                    .collect(Collectors.toList());
        }
    }

    @Override
    @Scheduled(cron = "0 17 16 * * *")
    @Async
    @Transactional(propagation = Propagation.REQUIRED)
    public void processEntrantsAllFaculty() {
        if (logger.isDebugEnabled())
            logger.debug("Scheduled process started!");
        List<Faculty> faculties = facultyRepository.findAll().stream()
                .filter(f -> f.getEvaluationDate().plusDays(1).isEqual(LocalDate.now()))
                .collect(Collectors.toList());
        for (Faculty faculty : faculties) {
            List<Person> persons = personRepository.findAllByFaculty_Id(faculty.getId());
            List<Person> student = new ArrayList<>();
            List<Person> passerby = new ArrayList<>();
            Integer minPositiveResult = -1;
            if (!persons.isEmpty()) {
                for (Person person : persons) {
                    if (person.getFaculty().getPassingScore() <= person.getEnlist().getTotalScore()) {
                        student.add(person);
                    } else {
                        passerby.add(person);
                    }
                }
                if(student.size() > faculty.getNumberOfStudents()) {
                    student = student.stream()
                            .sorted((s1, s2) -> s2.getEnlist().getTotalScore().compareTo(s1.getEnlist().getTotalScore()))
                            .collect(Collectors.toList());
                    int condition = student.get(faculty.getNumberOfStudents() - 1).getEnlist().getTotalScore();
                    if(persons.stream().filter(p -> p.getEnlist().getTotalScore() == condition).count() > 1) {
                        minPositiveResult = condition;
                    }
//                    if(logger.isDebugEnabled())
//                        logger.debug("!!!Student = " + student + ", minResult = " + minPositiveResult);
                    for (int i = faculty.getNumberOfStudents(); i < student.size(); i++) {
                        passerby.add(student.get(i));
                    }
                    student = student.stream().limit(3).collect(Collectors.toList());
                }
//                if(logger.isDebugEnabled())
//                    logger.debug("---Student = " + student);
//                if(logger.isDebugEnabled())
//                    logger.debug("+++Passerby = " + passerby);
                for (Person person: student) {
                    if(person.getEnlist().getTotalScore() == minPositiveResult) {
                        person.getEnlist().setAction(ActionEnum.INTERVIEW);
                    } else {
                        person.getEnlist().setAction(ActionEnum.YES);
                        person.getEnlist().setReceiptDate(LocalDate.now());
                    }
                    saveEntrantsAndSendMail(person);
                }
                for (Person person: passerby) {
                    if(person.getEnlist().getTotalScore() == minPositiveResult) {
                        person.getEnlist().setAction(ActionEnum.INTERVIEW);
                    } else {
                        person.getEnlist().setAction(ActionEnum.NO);
                    }
                    saveEntrantsAndSendMail(person);
                }
            } else {
                if (logger.isDebugEnabled())
                    logger.debug("List of entrants is empty!!!!!");
            }
        }
    }

    private void saveEntrantsAndSendMail(Person person) {
        try {
            personRepository.savePerson(person);
        } catch(PersonSaveException e) {
            logger.error("ResultService: catch person save or mail exception", e);
            throw new ServiceException(e.getMessage());
        }
    }

}
