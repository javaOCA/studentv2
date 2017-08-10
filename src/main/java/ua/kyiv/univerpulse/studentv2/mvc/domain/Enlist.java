package ua.kyiv.univerpulse.studentv2.mvc.domain;

import ua.kyiv.univerpulse.studentv2.mvc.dto.EnlistDto;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "enlist")
public class Enlist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer totalScore;
    @Enumerated(EnumType.STRING)
    private ActionEnum action;
    private LocalDate receiptDate;
    @OneToOne(mappedBy = "enlist")
    private Person person;

    public Enlist() {}

    public static class Builder {

        Enlist enlist = new Enlist();

        public Builder setId(EnlistDto enlistDto) {
            enlist.setId(enlistDto.getId());
            return this;
        }

        public Builder setTotalScore(EnlistDto enlistDto) {
            enlist.setTotalScore(enlistDto.getTotalScore());
            return this;
        }

        public Builder setAction(EnlistDto enlistDto) {
            enlist.setAction(enlistDto.getAction());
            return this;
        }

        public Builder setReceiptDate(EnlistDto enlistDto) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            enlist.setReceiptDate(LocalDate.parse(enlistDto.getReceiptDate(), formatter));
            return this;
        }

        public Enlist build() { return enlist; }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(Integer totalScore) {
        this.totalScore = totalScore;
    }

    public ActionEnum getAction() {
        return action;
    }

    public void setAction(ActionEnum action) {
        this.action = action;
    }

    public LocalDate getReceiptDate() {
        return receiptDate;
    }

    public void setReceiptDate(LocalDate receiptDate) {
        this.receiptDate = receiptDate;
    }

    public Person getPerson() { return person; }

    public void setPerson(Person person) { this.person = person; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Enlist enlist = (Enlist) o;

        if (!id.equals(enlist.id)) return false;
        if (totalScore != null ? !totalScore.equals(enlist.totalScore) : enlist.totalScore != null) return false;
        if (action != enlist.action) return false;
        return receiptDate != null ? receiptDate.equals(enlist.receiptDate) : enlist.receiptDate == null;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + (totalScore != null ? totalScore.hashCode() : 0);
        result = 31 * result + (action != null ? action.hashCode() : 0);
        result = 31 * result + (receiptDate != null ? receiptDate.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Enlist{" +
                "id=" + id +
                ", totalScore=" + totalScore +
                ", action=" + action +
                ", receiptDate=" + receiptDate +
                '}';
    }
}
