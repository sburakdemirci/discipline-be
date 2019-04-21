package com.disciplinebe.disciplinebe.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Date;


public class EventModelRequest {


    @JsonProperty
    private int user_id;

    @JsonProperty
    private String event_name;

    @JsonProperty
    private Date event_date;

    @JsonProperty
    private int time_start;  // time slots 0-1440 . ın minutes

    @JsonProperty
    private int time_finish;   // time slots 0-1440 . ın minutes

    @JsonProperty
    private boolean notification_enabled;

    @JsonProperty
    private int notification_time;

    @JsonProperty
    private String event_note;

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
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

    public void setEvent_date(Date event_date) {
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

    public String getEvent_note() {
        return event_note;
    }

    public void setEvent_note(String event_note) {
        this.event_note = event_note;
    }
}
