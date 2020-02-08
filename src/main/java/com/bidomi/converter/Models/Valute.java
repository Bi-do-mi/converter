package com.bidomi.converter.Models;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

@Embeddable
public class Valute {
    @Column(name = "id_sign")
    String id;
    Integer numCode;
    String charCode;
    Integer nominal;
    String name;
    double value;


    public String getId() {
        return id;
    }

    @JacksonXmlProperty(localName = "ID")
    public void setId(String id) {
        this.id = id;
    }

    public Integer getNumCode() {
        return numCode;
    }

    @JacksonXmlProperty(localName = "NumCode")
    public void setNumCode(String numCode) {
        this.numCode = Integer.decode(numCode);
    }

    public String getCharCode() {
        return charCode;
    }

    @JacksonXmlProperty(localName = "CharCode")
    public void setCharCode(String charCode) {
        this.charCode = charCode;
    }

    public Integer getNominal() {
        return nominal;
    }

    @JacksonXmlProperty(localName = "Nominal")
    public void setNominal(String nominal) {
        this.nominal = Integer.decode(nominal);
    }

    public String getName() {
        return name;
    }

    @JacksonXmlProperty(localName = "Name")
    public void setName(String name) {
        this.name = name;
    }

    public double getValue() {
        return value;
    }

    @JacksonXmlProperty(localName = "Value")
    public void setValue(String value) {
        try {
            NumberFormat f = NumberFormat.getInstance(Locale.FRANCE);
            this.value = f.parse(value).doubleValue();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "\nid: " + getId() +
                "\n numCode: " + getCharCode() +
                "\n charCode: " + getCharCode() +
                "\n nominal: " + getNominal() +
                "\n name: " + getName() +
                "\n value: " + getValue();
    }
}
