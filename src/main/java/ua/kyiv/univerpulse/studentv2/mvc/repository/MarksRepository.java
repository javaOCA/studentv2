package ua.kyiv.univerpulse.studentv2.mvc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ua.kyiv.univerpulse.studentv2.mvc.domain.Marks;

@Repository
public interface MarksRepository extends JpaRepository<Marks, Long> {

//    void saveMarks(Marks marks);

    Marks findMarksById(Long id);

//    @Query("select m from Marks m where m.id = :marksId")
//    Marks findMarksById(@Param("marksId") Long id);

}

