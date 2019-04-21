package com.disciplinebe.disciplinebe.database.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;


@Entity
@Table(name = "quotes")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})

public class QuotesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int quote_id;

    @Column
    private String quote_string;

    public int getQuote_id() {
        return quote_id;
    }

    public void setQuote_id(int quote_id) {
        this.quote_id = quote_id;
    }

    public String getQuote_string() {
        return quote_string;
    }

    public void setQuote_string(String quote_string) {
        this.quote_string = quote_string;
    }
}








