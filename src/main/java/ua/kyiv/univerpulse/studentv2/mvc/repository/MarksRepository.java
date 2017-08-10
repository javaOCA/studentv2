package ua.kyiv.univerpulse.studentv2.mvc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.kyiv.univerpulse.studentv2.mvc.domain.Marks;

@Repository
public interface MarksRepository extends JpaRepository<Marks, Long> {

    Marks findMarksById(Long id);

}

