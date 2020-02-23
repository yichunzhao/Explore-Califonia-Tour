package com.ynz.ec.explorecali.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Objects;


@Embeddable
//composite key class must be public
//it must define hashcode and equals methods
//must have a non-argument constructor
//must be serializable
public class TourRatingPK implements Serializable {

    @ManyToOne
    private Tour tour;

    @Column(insertable = false, updatable = false, nullable = false)
    private Integer customerId;

    //JPA composite primary key convention: must have a non-arg constructor.
    public TourRatingPK() {
    }

    public TourRatingPK(Tour tour, Integer customerId) {
        this.tour = tour;
        this.customerId = customerId;
    }

    public Tour getTour() {
        return tour;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TourRatingPK that = (TourRatingPK) o;
        return Objects.equals(tour, that.tour) &&
                Objects.equals(customerId, that.customerId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tour, customerId);
    }
}
