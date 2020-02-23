package com.ynz.ec.explorecali.domain;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class TourRating {

    @EmbeddedId
    private TourRatingPK ratingPK;

    @Column(nullable = false)
    private Integer score;

    @Column
    private String comment;

    /**
     * @param ratingPK primary key of a tour and customer id.
     * @param score    Integer score(1-5)
     * @param comment  Optional comment from the customer
     */
    public TourRating(TourRatingPK ratingPK, Integer score, String comment) {
        this.ratingPK = ratingPK;
        this.score = score;
        this.comment = comment;
    }

    public TourRatingPK getRatingPK() {
        return ratingPK;
    }

    public Integer getScore() {
        return score;
    }

    public String getComment() {
        return comment;
    }

    @Override
    public String toString() {
        return "TourRating{" +
                "ratingPK=" + ratingPK +
                ", score=" + score +
                ", comment='" + comment + '\'' +
                '}';
    }
}
