package ua.kyiv.univerpulse.studentv2.mvc.repository.impl;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ua.kyiv.univerpulse.studentv2.mvc.domain.Person;
import ua.kyiv.univerpulse.studentv2.mvc.repository.PersonRepositoryCustom;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class PersonRepositoryImpl implements PersonRepositoryCustom {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void savePerson(Person person) {
        try {
            em.persist(person);
            em.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
