package ua.kyiv.univerpulse.studentv2.mvc.dto;

import ua.kyiv.univerpulse.studentv2.mvc.domain.ActionEnum;
import ua.kyiv.univerpulse.studentv2.mvc.domain.Enlist;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class EnlistDto {

    private Long id;
    private Integer totalScore;
    private ActionEnum action;
    private String receiptDate;

    public EnlistDto() {}

    public static class Builder {

        EnlistDto enlistDto = new EnlistDto();

        public Builder setId(Enlist enlist) {
            enlistDto.setId(enlist.getId());
            return this;
        }

        public Builder setTotalScore(Enlist enlist) {
            enlistDto.setTotalScore(enlist.getTotalScore());
            return this;
        }

        public Builder setAction(Enlist enlist) {
            enlistDto.setAction(enlist.getAction());
            return this;
        }

        public Builder setReceiptDate(Enlist enlist) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            enlistDto.setReceiptDate(enlist.getReceiptDate().format(formatter));
            return this;
        }

        public EnlistDto build() { return enlistDto; }
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

    public String getReceiptDate() {
        return receiptDate;
    }

    public void setReceiptDate(String receiptDate) {
        this.receiptDate = receiptDate;
    }

    @Override
    public String toString() {
        return "EnlistDto{" +
                "id=" + id +
                ", totalScore=" + totalScore +
                ", action=" + action +
                ", receiptDate=" + receiptDate +
                '}';
    }
}
