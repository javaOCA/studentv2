package ua.kyiv.univerpulse.studentv2.mvc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ua.kyiv.univerpulse.studentv2.mvc.domain.Person;

import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long>, PersonRepositoryCustom {

    Person findPersonByLogin(String login);

    @Query("select p from Person p join p.address a where p.firstName = :firstName and p.lastName = :lastName " +
            "and a.city = :city and a.street = :street and a.home = :home and a.apartment = :apartment")
    Person findPersonByNameAndAddress(@Param("firstName") String firstName,
                                      @Param("lastName") String lastName,
                                      @Param("city") String city,
                                      @Param("street") String street,
                                      @Param("home") String home,
                                      @Param("apartment") String apartment);
    @Query("select p from Person p where p.role.role <> ua.kyiv.univerpulse.studentv2.mvc.domain.RoleEnum.ROLE_ADMIN")
    List<Person> getAllPersonWithoutAdmin();
}
