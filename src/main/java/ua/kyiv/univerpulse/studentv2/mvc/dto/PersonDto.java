package ua.kyiv.univerpulse.studentv2.mvc.dto;

import ua.kyiv.univerpulse.studentv2.mvc.domain.Person;
import ua.kyiv.univerpulse.studentv2.mvc.domain.Role;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.format.DateTimeFormatter;

public class PersonDto {
    private Long id;
    @NotNull
    @Pattern(regexp = "\\w{2,}", message = "{message.login.err}")
    private String login;
    @NotNull
    @Pattern(regexp = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})",
            message = "{message.password.err}")
    private String password;
    @NotNull
    @Pattern(regexp = "\\w{2,}", message = "{message.firstname.err}")
    private String firstName;
    @NotNull
    @Pattern(regexp = "\\w{2,}", message = "{message.lastname.err}")
    private String lastName;
    @NotNull
    @Pattern(regexp = "^[0-9]{4}-(0[1-9]|1[012])-(0[1-9]|1[0-9]|2[0-9]|3[01])$", message = "{message.date.err}")
    private String birthday;
    @Pattern(regexp = "^(?!.*?none).*$", message = "{message.education.err}")
    private String education;
    @NotNull
    @Pattern(regexp = "^\\+{1}\\d{1,2}\\(\\d{3}\\)\\d{7}$", message = "{message.phone.err}")
    private String phone;
    @NotNull
    @Pattern(regexp = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$",
            message = "{message.email.err}")
    private String email;
    private Role role;

    public PersonDto() {}

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

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public static class Builder {
        PersonDto personDto = new PersonDto();

        public Builder setId(Person person) {
            personDto.setId(person.getId());
            return this;
        }

        public Builder setLogin(Person person) {
            personDto.setLogin(person.getLogin());
            return this;
        }

        public Builder setPassword(Person person) {
            personDto.setPassword(person.getPassword());
            return this;
        }

        public Builder setFirstName(Person person) {
            personDto.setFirstName(person.getFirstName());
            return this;
        }

        public Builder setLastName(Person person) {
            personDto.setLastName(person.getLastName());
            return this;
        }

        public Builder setEducation(Person person) {
            personDto.setEducation(person.getEducation());
            return this;
        }

        public Builder setBirthday(Person person) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            personDto.setBirthday((person.getBirthday()).format(formatter));
            return this;
        }

        public Builder setPhone(Person person) {
            personDto.setPhone(person.getPhone());
            return this;
        }

        public Builder setEmail(Person person) {
            personDto.setEmail(person.getEmail());
            return this;
        }

        public Builder setRole(Person person) {
            personDto.setRole(person.getRole());
            return this;
        }

        public PersonDto build() { return personDto; }
    }

    @Override
    public String toString() {
        return "PersonDto{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthday=" + birthday +
                ", education='" + education + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
