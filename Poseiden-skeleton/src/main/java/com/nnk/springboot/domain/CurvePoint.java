package com.nnk.springboot.domain;

import com.nnk.springboot.Constants;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;


@Entity
@Table(name = "curvepoint")
public class CurvePoint {
    // TODO: Map columns in data table CURVEPOINT with corresponding java fields
    @Id
    @GeneratedValue
    private Integer id;
    @NotNull(message = "Curve Id must not be null")
    @Column(name = "curveId")
    private Integer curveId;
    @Column(name = "asOfDate")
    @DateTimeFormat(pattern = Constants.DATE_PATTERN)
    private Date asOfDate;
    @Column(name = "term")
    private Double term;
    @Column(name = "value")
    private Double value;
    @Column(name = "creationDate")
    @DateTimeFormat(pattern = Constants.DATE_PATTERN)
    private Date creationDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCurveId() {
        return curveId;
    }

    public void setCurveId(Integer curveId) {
        this.curveId = curveId;
    }

    public Date getAsOfDate() {
        return asOfDate;
    }

    public void setAsOfDate(Date asOfDate) {
        this.asOfDate = asOfDate;
    }

    public Double getTerm() {
        return term;
    }

    public void setTerm(Double term) {
        this.term = term;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    @Override
    public String toString() {
        return "CurvePoint{" +
                "id=" + id +
                ", curveId=" + curveId +
                ", asOfDate=" + asOfDate +
                ", term=" + term +
                ", value=" + value +
                ", creationDate=" + creationDate +
                '}';
    }
}
