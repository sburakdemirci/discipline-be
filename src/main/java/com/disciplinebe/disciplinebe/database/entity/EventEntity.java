package com.disciplinebe.disciplinebe.database.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.sql.Date;


@Entity
@Table(name = "user_event")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})

public class EventEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int event_id;

    @JsonManagedReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UsersEntity user_id;

    @Column
    private String event_name;

    @Column
    private Date event_date; // days can be numbers. eg. 1 for monday. so monday and tuesday 12.

    @Column
    private int time_start;  // time slots 0-1440 . ın minutes

    @Column
    private int time_finish;   // time slots 0-1440 . ın minutes

    @Column
    private boolean notification_enabled;

    @Column
    private int notification_time;

    @Column
    private String event_note;

    public int getEvent_id() {
        return event_id;
    }

    public void setEvent_id(int event_id) {
        this.event_id = event_id;
    }

    public UsersEntity getUser_id() {
        return user_id;
    }

    public void setUser_id(UsersEntity user_id) {
        this.user_id = user_id;
    }

    public String getEvent_name() {
        return event_name;
    }

    public void setEvent_name(String event_name) {
        this.event_name = event_name;
    }

    public Date getEvent_date() {
        return event_date;
    }

    public void setEvent_date(java.sql.Date event_date) {
        this.event_date = event_date;
    }

    public int getTime_start() {
        return time_start;
    }

    public void setTime_start(int time_start) {
        this.time_start = time_start;
    }

    public int getTime_finish() {
        return time_finish;
    }

    public void setTime_finish(int time_finish) {
        this.time_finish = time_finish;
    }

    public boolean isNotification_enabled() {
        return notification_enabled;
    }

    public void setNotification_enabled(boolean notification_enabled) {
        this.notification_enabled = notification_enabled;
    }

    public int getNotification_time() {
        return notification_time;
    }

    public void setNotification_time(int notification_time) {
        this.notification_time = notification_time;
    }

    public void setEvent_note(String event_note) {
        this.event_note = event_note;
    }


}





