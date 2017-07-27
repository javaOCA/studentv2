package ua.kyiv.univerpulse.studentv2.mvc.dto;

import org.hibernate.validator.constraints.Range;
import ua.kyiv.univerpulse.studentv2.mvc.domain.Marks;

import javax.validation.constraints.Null;
import javax.validation.constraints.Pattern;

public class MarksDto {
    private Long id;
    @Range(max = 12L, message = "{message.marks.err}")
    private Integer algebra;
    @Range(max = 12L, message = "{message.marks.err}")
    private Integer geometry;
    @Range(max = 12L, message = "{message.marks.err}")
    private Integer physics;
    @Range(max = 12L, message = "{message.marks.err}")
    private Integer chemistry;
    @Range(max = 12L, message = "{message.marks.err}")
    private Integer biology;
    @Range(max = 12L, message = "{message.marks.err}")
    private Integer computer_science;
    @Range(max = 12L, message = "{message.marks.err}")
    private Integer english;
    @Range(max = 12L, message = "{message.marks.err}")
    private Integer geography;
    @Range(max = 12L, message = "{message.marks.err}")
    private Integer literature;
    @Null
    @Range(max = 12L, message = "{message.marks.err}")
    private Integer music;
    @Range(max = 12L, message = "{message.marks.err}")
    private Integer drawing;

    public MarksDto() {}

    public static class Builder {
        MarksDto marksDto = new MarksDto();

        public Builder setId(Marks marks) {
            marksDto.setId((marks.getId()));
            return this;
        }

        public Builder setAlgebry(Marks marks) {
            marksDto.setAlgebra((marks.getAlgebra()));
            return this;
        }

        public Builder setGeometry(Marks marks) {
            marksDto.setGeometry((marks.getGeometry()));
            return this;
        }

        public Builder setPhysics(Marks marks) {
            marksDto.setPhysics(marks.getPhysics());
            return this;
        }

        public Builder setChemistry(Marks marks) {
            marksDto.setChemistry((marks.getChemistry()));
            return this;
        }

        public Builder setBiology(Marks marks) {
            marksDto.setBiology((marks.getBiology()));
            return this;
        }

        public Builder setComputer_science(Marks marks) {
            marksDto.setComputer_science((marks.getComputer_science()));
            return this;
        }

        public Builder setEnglish(Marks marks) {
            marksDto.setEnglish((marks.getEnglish()));
            return this;
        }

        public Builder setGeography(Marks marks) {
            marksDto.setGeography((marks.getGeography()));
            return this;
        }

        public Builder setLiterature(Marks marks) {
            marksDto.setLiterature((marks.getLiterature()));
            return this;
        }

        public Builder setMusic(Marks marks) {
            marksDto.setMusic((marks.getMusic()));
            return this;
        }

        public Builder setDrawing(Marks marks) {
            marksDto.setDrawing((marks.getDrawing()));
            return this;
        }

        public MarksDto build() { return marksDto; }
    }

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

    @Override
    public String toString() {
        return "MarksDto{" +
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
                '}';
    }
}
