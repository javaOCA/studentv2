package ua.kyiv.univerpulse.studentv2.mvc.domain;

import ua.kyiv.univerpulse.studentv2.mvc.dto.PersonDto;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Entity
@Table(name = "person")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String login;
    private String password;
    private String firstName;
    private String lastName;
    private LocalDate birthday;
    private String education;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "person_id")
    private Address address;
    private String phone;
    private String email;
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "person_faculties",
    joinColumns = @JoinColumn(name = "person_id"),
    inverseJoinColumns = @JoinColumn(name = "faculty_id"))
    private List<Faculty> faculties;
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "marks_id")
    private Marks marks;
    @ManyToOne(fetch = FetchType.EAGER)
    private Role role;

    public Person() {}

    public static class Builder {
        Person person = new Person();

        public Builder setId(PersonDto personDto) {
            person.setId(personDto.getId());
            return this;
        }

        public Builder setLogin(PersonDto personDto) {
            person.setLogin(personDto.getLogin());
            return this;
        }

        public Builder setPassword(PersonDto personDto) {
            person.setPassword(personDto.getPassword());
            return this;
        }

        public Builder setFirstName(PersonDto personDto) {
            person.setFirstName(personDto.getFirstName());
            return this;
        }

        public Builder setLastName(PersonDto personDto) {
            person.setLastName(personDto.getLastName());
            return this;
        }

        public Builder setEducation(PersonDto personDto) {
            person.setEducation(personDto.getEducation());
            return this;
        }

        public Builder setBirthday(PersonDto personDto) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-mm-dd");
            person.setBirthday(LocalDate.parse(personDto.getBirthday(), formatter));
            return this;
        }

        public Builder setPhone(PersonDto personDto) {
            person.setPhone(personDto.getPhone());
            return this;
        }

        public Builder setEmail(PersonDto personDto) {
            person.setEmail(personDto.getEmail());
            return this;
        }

        public Person build() { return person; }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Faculty> getFaculties() {
        return faculties;
    }

    public void setFaculties(List<Faculty> faculties) {
        this.faculties = faculties;
    }

    public Marks getMarks() {
        return marks;
    }

    public void setMarks(Marks marks) {
        this.marks = marks;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Person person = (Person) o;

        if (!login.equals(person.login)) return false;
        if (!password.equals(person.password)) return false;
        if (!firstName.equals(person.firstName)) return false;
        if (!lastName.equals(person.lastName)) return false;
        return birthday.equals(person.birthday);
    }

    @Override
    public int hashCode() {
        int result = login.hashCode();
        result = 31 * result + password.hashCode();
        result = 31 * result + firstName.hashCode();
        result = 31 * result + lastName.hashCode();
        result = 31 * result + birthday.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthday=" + birthday +
                ", education='" + education + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", faculties=" + faculties +
                '}';
    }
}