package ua.kyiv.univerpulse.studentv2.mvc.domain;

import ua.kyiv.univerpulse.studentv2.mvc.dto.FacultyDto;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Entity
@Table(name = "faculty")
public class Faculty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToMany(mappedBy = "faculty")
    private List<Person> persons;
    private LocalDate evaluationDate;
    private Integer passingScore;
    private Integer numberOfStudents;

    public Faculty() {}

    public static class Builder {

        Faculty faculty = new Faculty();

        public Builder getId(FacultyDto facultyDto) {
            faculty.setId(facultyDto.getId());
            return this;
        }

        public Builder getName(FacultyDto facultyDto) {
            faculty.setName(facultyDto.getName());
            return this;
        }

        public Builder getPersons(FacultyDto facultyDto) {
            faculty.setPersons(facultyDto.getPersons());
            return this;
        }

        public Builder getEvaluationDate(FacultyDto facultyDto) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate date = LocalDate.parse(facultyDto.getEvaluationDate(), formatter);
            faculty.setEvaluationDate(date);
            return this;
        }

        public Builder getPassingScore(FacultyDto facultyDto) {
            faculty.setPassingScore(facultyDto.getPassingScore());
            return this;
        }

        public Builder getNumberOfStudents(FacultyDto facultyDto) {
            faculty.setNumberOfStudents(facultyDto.getNumberOfStudents());
            return this;
        }

        public Faculty build() { return faculty; }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Person> getPersons() {
        return persons;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }

    public LocalDate getEvaluationDate() {
        return evaluationDate;
    }

    public void setEvaluationDate(LocalDate evaluationDate) {
        this.evaluationDate = evaluationDate;
    }

    public Integer getPassingScore() { return passingScore; }

    public void setPassingScore(Integer passingScore) { this.passingScore = passingScore; }

    public Integer getNumberOfStudents() { return numberOfStudents; }

    public void setNumberOfStudents(Integer numberOfStudents) { this.numberOfStudents = numberOfStudents; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Faculty faculty = (Faculty) o;

        return name.equals(faculty.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public String toString() {
        return "Faculty{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", evaluationDate=" + evaluationDate +
                ", passingScore=" + passingScore +
                ", numberOfStudents=" + numberOfStudents +
                '}';
    }
}
