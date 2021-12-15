package com.nnk.springboot.domain;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table(name = "rating")
public class Rating {
    @Id
    @GeneratedValue
    private Integer id;
    @Column(name = "moodysRating")
    @NotBlank(message = "Type is mandatory")
    private String moodysRating;
    @Column(name = "sandPRating")
    private String sandPRating;
    @Column(name = "fitchRating")
    private String fitchRating;
    @Column(name = "orderNumber")
    @NotNull(message = " orderNumber must not be null")
    private Integer orderNumber;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Rating id (Integer id) {
        this.id = id;
        return this;
    }

    public String getMoodysRating() {
        return moodysRating;
    }

    public void setMoodysRating(String moodysRating) {
        this.moodysRating = moodysRating;
    }

    public Rating moodysRating (String moodysRating) {
        this.moodysRating = moodysRating;
        return this;
    }

    public String getSandPRating() {
        return sandPRating;
    }

    public void setSandPRating(String sandPRating) {
        this.sandPRating = sandPRating;
    }
    public Rating sandPRating (String sandPRating) {
        this.sandPRating = sandPRating;
        return this;
    }

    public String getFitchRating() {
        return fitchRating;
    }

    public void setFitchRating(String fitchRating) {
        this.fitchRating = fitchRating;
    }
    public Rating fitchRating (String fitchRating) {
        this.fitchRating = fitchRating;
        return this;
    }

    public Integer getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Integer orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Rating orderNumber (Integer orderNumber) {
        this.orderNumber = orderNumber;
        return this;
    }
    @Override
    public String toString() {
        return "Rating{" +
                "id=" + id +
                ", moodysRating='" + moodysRating + '\'' +
                ", sandPRating='" + sandPRating + '\'' +
                ", fitchRating='" + fitchRating + '\'' +
                ", orderNumber=" + orderNumber +
                '}';
    }
}
