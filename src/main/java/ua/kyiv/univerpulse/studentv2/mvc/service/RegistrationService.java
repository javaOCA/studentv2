package ua.kyiv.univerpulse.studentv2.mvc.service;

import org.springframework.web.multipart.MultipartFile;
import ua.kyiv.univerpulse.studentv2.mvc.domain.Faculty;
import ua.kyiv.univerpulse.studentv2.mvc.domain.FileInfo;
import ua.kyiv.univerpulse.studentv2.mvc.domain.Person;
import ua.kyiv.univerpulse.studentv2.mvc.dto.AddressDto;
import ua.kyiv.univerpulse.studentv2.mvc.dto.FacultyDto;
import ua.kyiv.univerpulse.studentv2.mvc.dto.MarksDto;
import ua.kyiv.univerpulse.studentv2.mvc.dto.PersonDto;

import java.util.List;

public interface RegistrationService {

    List<FileInfo> savePerson(PersonDto personDto, AddressDto addressDto, MarksDto marksDto, MultipartFile[] files);

    boolean findPersonByLogin(PersonDto personDto);

    Person findPersonByNameAndAddress(PersonDto personDto, AddressDto addressDto);

    List<Faculty> getAllFaculty();

    void updateFaculty(FacultyDto facultyDto);

    void saveFaculty(FacultyDto facultyDto);

    boolean findEvaluationDateByFaculty(PersonDto personDto);

    void updatePerson(PersonDto personDto);
}
