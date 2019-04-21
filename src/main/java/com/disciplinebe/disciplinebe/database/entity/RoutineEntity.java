package com.disciplinebe.disciplinebe.database.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;


@Entity
@Table(name = "user_routine")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})

public class RoutineEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int routine_id;

    @JsonManagedReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UsersEntity user_id;

    @Column
    private String routine_name;

    @Column
    private String selected_week_days; // days can be numbers. eg. 1 for monday. so monday and tuesday 12.

    @Column
    private int time_start;  // time slots 0-1440 . ın minutes

    @Column
    private int time_finish;   // time slots 0-1440 . ın minutes

    @Column
    private boolean notification_enabled;

    @Column
    private int notification_time;

    @Column
    private String routine_note;

    public int getRoutine_id() {
        return routine_id;
    }

    public void setRoutine_id(int routine_id) {
        this.routine_id = routine_id;
    }

    public UsersEntity getUser_id() {
        return user_id;
    }

    public void setUser_id(UsersEntity user_id) {
        this.user_id = user_id;
    }

    public String getRoutine_name() {
        return routine_name;
    }

    public void setRoutine_name(String routine_name) {
        this.routine_name = routine_name;
    }

    public String getSelected_days() {
        return selected_week_days;
    }

    public void setSelected_days(String selected_days) {
        this.selected_week_days = selected_days;
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

    public String getSelected_week_days() {
        return selected_week_days;
    }

    public void setSelected_week_days(String selected_week_days) {
        this.selected_week_days = selected_week_days;
    }

    public String getRoutine_note() {
        return routine_note;
    }

    public void setRoutine_note(String routine_note) {
        this.routine_note = routine_note;
    }
}





