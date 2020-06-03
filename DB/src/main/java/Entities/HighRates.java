package Entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@SuppressWarnings("PMD")
@Entity
@Table(name = "HighRates")
public class HighRates extends Rate {
    @Column(name = "nameOfCritic")
    private String nameOfCritic;

    public String getNameOfCritic() {
        return nameOfCritic;
    }

    public void setNameOfCritic(String nameOfCritic) {
        this.nameOfCritic = nameOfCritic;
    }

    public HighRates(){}
    public HighRates(int rate, String nameOfCritic) {
        super(rate);
        this.nameOfCritic = nameOfCritic;
    }
}
