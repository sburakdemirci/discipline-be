package com.disciplinebe.disciplinebe.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Date;


public class GoalModelRequest {




    @JsonProperty
    private int user_id;

    @JsonProperty
    private String goal_name;

    @JsonProperty
    private int weekly_work_hours;

    @JsonProperty
    private Date deadline;

    @JsonProperty
    private String selected_week_days; //will be  12 for monday and tuesday

    @JsonProperty
    private int time_zone_starts;

    @JsonProperty
    private int time_zone_finish;

    @JsonProperty
    private long calculated_hours;

    @JsonProperty
    private long complated_hours; // buraya null değil 0 ver

    @JsonProperty
    private int order_of_priority;

    @JsonProperty
    private String goal_note;

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getGoal_name() {
        return goal_name;
    }

    public void setGoal_name(String goal_name) {
        this.goal_name = goal_name;
    }

    public int getWeekly_work_hours() {
        return weekly_work_hours;
    }

    public void setWeekly_work_hours(int weekly_work_hours) {
        this.weekly_work_hours = weekly_work_hours;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public void setSelected_week_days(String selected_week_days) {
        this.selected_week_days = selected_week_days;
    }

    public String getSelected_week_days() {
        return selected_week_days;
    }

    public int getTime_zone_starts() {
        return time_zone_starts;
    }

    public void setTime_zone_starts(int time_zone_starts) {
        this.time_zone_starts = time_zone_starts;
    }

    public int getTime_zone_finish() {
        return time_zone_finish;
    }

    public void setTime_zone_finish(int time_zone_finish) {
        this.time_zone_finish = time_zone_finish;
    }

    public long getCalculated_hours() {
        return calculated_hours;
    }

    public void setCalculated_hours(long calculated_hours) {
        this.calculated_hours = calculated_hours;
    }

    public long getComplated_hours() {
        return complated_hours;
    }

    public void setComplated_hours(long complated_hours) {
        this.complated_hours = complated_hours;
    }

    public int getOrder_of_priority() {
        return order_of_priority;
    }

    public void setOrder_of_priority(int order_of_priority) {
        this.order_of_priority = order_of_priority;
    }

    public String getGoal_note() {
        return goal_note;
    }

    public void setGoal_note(String goal_note) {
        this.goal_note = goal_note;
    }
}
