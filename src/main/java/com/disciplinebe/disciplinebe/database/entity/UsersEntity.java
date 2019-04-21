package com.disciplinebe.disciplinebe.database.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.sql.Date;

@Entity
@Table(name = "users")

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})


public class UsersEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @NotBlank
    private String name;

    @NotBlank
    private String email;

    @NotBlank
    private String password;

    @Column
    private Date birth_date;

    @Column
    private String job;

    @Column
    private int discipline_level;



    @JsonManagedReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "quote_id", nullable = false)
    private QuotesEntity quote_id;



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(Date birth_date) {
        this.birth_date = birth_date;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public int getDiscipline_level() {
        return discipline_level;
    }

    public void setDiscipline_level(int discipline_level) {
        this.discipline_level = discipline_level;
    }

    public QuotesEntity getQuote_id() {
        return quote_id;
    }

    public void setQuote_id(QuotesEntity quote_id) {
        this.quote_id = quote_id;
    }
}
