package com.bidomi.converter.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table(name = "curs")
@JsonIgnoreProperties(ignoreUnknown = true)
public class DaylyCurs {
    @Id
    @GeneratedValue(generator = "CURS_ID_GENERATOR")
    Long id;

    String date;
    String name;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "valute")
    @org.hibernate.annotations.CollectionId(
            columns = @Column(name = "valute_id"),
            type = @org.hibernate.annotations.Type(type = "long"),
            generator = "VALUTE_ID_GENERATOR")
    Collection<Valute> valute = new ArrayList<>();

    public String getDate() {
        return date;
    }

    @JacksonXmlProperty(localName = "Date", isAttribute = true)
    public void setDate(String date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    @JacksonXmlProperty(isAttribute = true)
    public void setName(String name) {
        this.name = name;
    }

    public Collection<com.bidomi.converter.Models.Valute> getValute() {
        return valute;
    }

    @JacksonXmlProperty(localName = "Valute")
    @JacksonXmlElementWrapper(useWrapping = false)
    public void setValute(ArrayList<com.bidomi.converter.Models.Valute> valute) {
        this.valute = valute;
    }

    @Override
    public String toString() {
        return "date: " + getDate() +
                "\n name: " + getName() +
                "\n valute: \n" + getValute().toString();
    }
}
