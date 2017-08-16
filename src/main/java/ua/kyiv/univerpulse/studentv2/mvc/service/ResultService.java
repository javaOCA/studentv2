package ua.kyiv.univerpulse.studentv2.mvc.service;

import ua.kyiv.univerpulse.studentv2.mvc.dto.MarksDto;
import ua.kyiv.univerpulse.studentv2.mvc.dto.PersonDto;

import java.util.List;

public interface ResultService {

    Integer getTotalScoring(PersonDto personDto);

    Integer getTotalResult(MarksDto marksDto);

    List<PersonDto> getAllPersonWithoutAdminWithEnlist();

    void processEntrantsAllFaculty();

}
