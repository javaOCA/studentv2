package ua.kyiv.univerpulse.studentv2.mvc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ua.kyiv.univerpulse.studentv2.mvc.domain.Faculty;

import java.sql.Date;
import java.util.List;

@Repository
public interface FacultyRepository extends JpaRepository<Faculty, Long>, FacultyRepositoryCustom {

    @Query("select f from Faculty f")
    List<Faculty> getAllFaculty();

    Faculty findFacultyByName(String name);

    @Query("select f.evaluationDate from Faculty f where f.name = :name")
    Date getEvaluationDateByName(@Param("name") String name);
}
