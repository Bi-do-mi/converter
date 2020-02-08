package com.bidomi.converter.Models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Convertation {
    @Id
    @GeneratedValue(generator = "CONVERTATION_ID_GENERATOR")
    Long id;
    @Column(nullable = false)
    String sourceCurrency;
    @Column(nullable = false)
    String targetCurrency;
    @Column(nullable = false)
    double sourceAmount;
    @Column(nullable = false)
    double calculatedAmount;
    @Column(nullable = false)
    String date;

    public String getSourceCurrency() {
        return sourceCurrency;
    }

    public void setSourceCurrency(String sourceCurrency) {
        this.sourceCurrency = sourceCurrency;
    }

    public String getTargetCurrency() {
        return targetCurrency;
    }

    public void setTargetCurrency(String targetCurrency) {
        this.targetCurrency = targetCurrency;
    }

    public double getSourceAmount() {
        return sourceAmount;
    }

    public void setSourceAmount(double sourceAmount) {
        this.sourceAmount = sourceAmount;
    }

    public double getCalculatedAmount() {
        return calculatedAmount;
    }

    public void setCalculatedAmount(double calculatedAmount) {
        this.calculatedAmount = calculatedAmount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Convertation{" +
                "id=" + id +
                ", sourceCurrency='" + sourceCurrency + '\'' +
                ", targetCurrency='" + targetCurrency + '\'' +
                ", sourceAmount=" + sourceAmount +
                ", calculatedAmount=" + calculatedAmount +
                ", date='" + date + '\'' +
                '}';
    }
}
