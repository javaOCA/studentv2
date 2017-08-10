package ua.kyiv.univerpulse.studentv2.mvc.repository;

import ua.kyiv.univerpulse.studentv2.mvc.domain.Faculty;

public interface FacultyRepositoryCustom {

    void saveFaculty(Faculty faculty);

    void updateFaculty(Faculty faculty);

}
