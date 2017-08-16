package ua.kyiv.univerpulse.studentv2.mvc.repository.impl;

import org.apache.log4j.Logger;
import ua.kyiv.univerpulse.studentv2.mvc.domain.Person;
import ua.kyiv.univerpulse.studentv2.mvc.repository.PersonRepositoryCustom;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class PersonRepositoryImpl implements PersonRepositoryCustom {

    private final static Logger logger = Logger.getLogger(PersonRepositoryImpl.class);

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

    @Override
    public void updatePerson(Person person) {
        try {
            Person p = (Person) em.find(Person.class, person.getId());
            p.setFirstName(person.getFirstName());
            p.setLastName(person.getLastName());
            p.setEmail(person.getEmail());
            person.setPhone(person.getPhone());
            em.merge(p);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
