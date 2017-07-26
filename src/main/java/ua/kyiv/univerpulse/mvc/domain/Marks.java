package ua.kyiv.univerpulse.mvc.domain;

import javax.persistence.*;

@Entity
@Table(name = "marks")
public class Marks {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer algebra;
    private Integer geometry;
    private Integer physics;
    private Integer chemistry;
    private Integer biology;
    private Integer computer_science;
    private Integer english;
    private Integer geography;
    private Integer literature;
    private Integer music;
    private Integer drawing;
    @OneToOne(mappedBy = "marks")
    private Person person;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getAlgebra() {
        return algebra;
    }

    public void setAlgebra(Integer algebra) {
        this.algebra = algebra;
    }

    public Integer getGeometry() {
        return geometry;
    }

    public void setGeometry(Integer geometry) {
        this.geometry = geometry;
    }

    public Integer getPhysics() {
        return physics;
    }

    public void setPhysics(Integer physics) {
        this.physics = physics;
    }

    public Integer getChemistry() {
        return chemistry;
    }

    public void setChemistry(Integer chemistry) {
        this.chemistry = chemistry;
    }

    public Integer getBiology() {
        return biology;
    }

    public void setBiology(Integer biology) {
        this.biology = biology;
    }

    public Integer getComputer_science() {
        return computer_science;
    }

    public void setComputer_science(Integer computer_science) {
        this.computer_science = computer_science;
    }

    public Integer getEnglish() {
        return english;
    }

    public void setEnglish(Integer english) {
        this.english = english;
    }

    public Integer getGeography() {
        return geography;
    }

    public void setGeography(Integer geography) {
        this.geography = geography;
    }

    public Integer getLiterature() {
        return literature;
    }

    public void setLiterature(Integer literature) {
        this.literature = literature;
    }

    public Integer getMusic() {
        return music;
    }

    public void setMusic(Integer music) {
        this.music = music;
    }

    public Integer getDrawing() {
        return drawing;
    }

    public void setDrawing(Integer drawing) {
        this.drawing = drawing;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Marks marks = (Marks) o;

        if (!algebra.equals(marks.algebra)) return false;
        if (!geometry.equals(marks.geometry)) return false;
        if (!physics.equals(marks.physics)) return false;
        if (!chemistry.equals(marks.chemistry)) return false;
        if (!biology.equals(marks.biology)) return false;
        if (!computer_science.equals(marks.computer_science)) return false;
        if (!english.equals(marks.english)) return false;
        if (!geography.equals(marks.geography)) return false;
        if (!literature.equals(marks.literature)) return false;
        if (!music.equals(marks.music)) return false;
        if (!drawing.equals(marks.drawing)) return false;
        return person.equals(marks.person);
    }

    @Override
    public int hashCode() {
        int result = algebra.hashCode();
        result = 31 * result + geometry.hashCode();
        result = 31 * result + physics.hashCode();
        result = 31 * result + chemistry.hashCode();
        result = 31 * result + biology.hashCode();
        result = 31 * result + computer_science.hashCode();
        result = 31 * result + english.hashCode();
        result = 31 * result + geography.hashCode();
        result = 31 * result + literature.hashCode();
        result = 31 * result + music.hashCode();
        result = 31 * result + drawing.hashCode();
        result = 31 * result + person.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Marks{" +
                "id=" + id +
                ", algebra=" + algebra +
                ", geometry=" + geometry +
                ", physics=" + physics +
                ", chemistry=" + chemistry +
                ", biology=" + biology +
                ", computer_science=" + computer_science +
                ", english=" + english +
                ", geography=" + geography +
                ", literature=" + literature +
                ", music=" + music +
                ", drawing=" + drawing +
                ", person=" + person +
                '}';
    }
}
