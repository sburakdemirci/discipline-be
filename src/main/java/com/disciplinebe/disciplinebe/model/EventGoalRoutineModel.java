package com.disciplinebe.disciplinebe.model;

import java.sql.Date;

public class EventGoalRoutineModel {

    private String name;
    private String type;
    private Date date;
    private int id;
    private int startSlotTime;
    private int duration;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStartSlotTime() {
        return startSlotTime;
    }

    public void setStartSlotTime(int startSlotTime) {
        this.startSlotTime = startSlotTime;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}
