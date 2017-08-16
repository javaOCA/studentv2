package ua.kyiv.univerpulse.studentv2.mvc.repository.impl;

import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ua.kyiv.univerpulse.studentv2.mvc.domain.Faculty;
import ua.kyiv.univerpulse.studentv2.mvc.repository.FacultyRepositoryCustom;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class FacultyRepositoryImpl implements FacultyRepositoryCustom {

    private final static Logger logger = Logger.getLogger(FacultyRepositoryImpl.class);

    @PersistenceContext
    private EntityManager em;

    @Override
    public void saveFaculty(Faculty faculty) {
        try {
            em.persist(faculty);
            em.flush();
            if (logger.isDebugEnabled())
                logger.debug("Save entity Faculty in DB " + faculty);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateFaculty(Faculty faculty) {
        try {
            Faculty fc = (Faculty) em.find(Faculty.class, faculty.getId());
            fc.setName(faculty.getName());
            fc.setEvaluationDate(faculty.getEvaluationDate());
            fc.setPassingScore(faculty.getPassingScore());
            fc.setNumberOfStudents(faculty.getNumberOfStudents());
            em.merge(fc);
            if (logger.isDebugEnabled())
                logger.debug("Update entity Faculty in DB " + faculty);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
