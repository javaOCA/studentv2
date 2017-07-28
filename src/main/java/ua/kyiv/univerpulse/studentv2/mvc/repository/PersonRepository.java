package ua.kyiv.univerpulse.studentv2.mvc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.kyiv.univerpulse.studentv2.mvc.domain.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long>, PersonRepositoryCustom {

    Person findPersonByLogin(String login);

}
