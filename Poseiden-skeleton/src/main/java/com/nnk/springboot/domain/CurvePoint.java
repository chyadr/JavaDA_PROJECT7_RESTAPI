package com.nnk.springboot.domain;

import com.nnk.springboot.constants.Constants;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;


@Entity
@Table(name = "curvepoint")
public class CurvePoint {
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

    public CurvePoint id (Integer id) {
        this.id = id;
        return this;
    }

    public Integer getCurveId() {
        return curveId;
    }

    public void setCurveId(Integer curveId) {
        this.curveId = curveId;
    }

    public CurvePoint curveId (Integer curveId) {
        this.curveId = curveId;
        return this;
    }

    public Date getAsOfDate() {
        return asOfDate;
    }

    public void setAsOfDate(Date asOfDate) {
        this.asOfDate = asOfDate;
    }
    public CurvePoint asOfDate (Date asOfDate) {
        this.asOfDate = asOfDate;
        return this;
    }


    public Double getTerm() {
        return term;
    }

    public void setTerm(Double term) {
        this.term = term;
    }

    public CurvePoint term (double term) {
        this.term = term;
        return this;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public CurvePoint value (Double value) {
        this.value = value;
        return this;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public CurvePoint creationDate (Date creationDate) {
        this.creationDate = creationDate;
        return this;
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
