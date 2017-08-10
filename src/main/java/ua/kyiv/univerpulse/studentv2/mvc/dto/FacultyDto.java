package ua.kyiv.univerpulse.studentv2.mvc.dto;

import ua.kyiv.univerpulse.studentv2.mvc.domain.Faculty;
import ua.kyiv.univerpulse.studentv2.mvc.domain.Person;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class FacultyDto {

    private Long id;
    private String name;
    private List<Person> persons;
    private String evaluationDate;
    private Integer passingScore;
    private Integer numberOfStudents;

    public FacultyDto() {}

    public static class Builder {

        FacultyDto facultyDto = new FacultyDto();

        public Builder getId(Faculty faculty) {
            facultyDto.setId(faculty.getId());
            return this;
        }

        public Builder getName(Faculty faculty) {
            facultyDto.setName(faculty.getName());
            return this;
        }

        public Builder getPersons(Faculty faculty) {
            facultyDto.setPersons(faculty.getPersons());
            return this;
        }

        public Builder getEvaluationDate(Faculty faculty) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate date = faculty.getEvaluationDate();
            facultyDto.setEvaluationDate(date.format(formatter));
            return this;
        }

        public Builder getPassingScore(Faculty faculty) {
            facultyDto.setPassingScore(faculty.getPassingScore());
            return this;
        }

        public Builder getNumberOfStudents(Faculty faculty) {
            facultyDto.setNumberOfStudents(faculty.getNumberOfStudents());
            return this;
        }

        public FacultyDto build() { return facultyDto; }
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

    public String getEvaluationDate() {
        return evaluationDate;
    }

    public void setEvaluationDate(String evaluationDate) {
        this.evaluationDate = evaluationDate;
    }

    public Integer getPassingScore() {
        return passingScore;
    }

    public void setPassingScore(Integer passingScore) {
        this.passingScore = passingScore;
    }

    public Integer getNumberOfStudents() { return numberOfStudents; }

    public void setNumberOfStudents(Integer numberOfStudents) { this.numberOfStudents = numberOfStudents; }

    @Override
    public String toString() {
        return "FacultyDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", evaluationDate='" + evaluationDate + '\'' +
                ", passingScore=" + passingScore +
                ", numberOfStudents=" + numberOfStudents +
                '}';
    }
}
