package com.disciplinebe.disciplinebe.database.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.sql.Date;


@Entity
@Table(name = "user_goal")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})

public class GoalEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int goal_id;

    @JsonManagedReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UsersEntity user_id;
//dikkat edersen burada diğer tablonun entity'sini column olarak veriyoruz.


    @Column
    private String goal_name;

    @Column
    private int weekly_work_hours;

    @Column
    private Date deadline;

    @Column
    private String free_week_days; //will be  12 for monday and tuesday

    @Column
    private int time_zone_starts;

    @Column
    private int time_zone_finish;

    @Column
    private int calculated_minutes;

    @Column
    private int complated_minutes; // buraya null değil 0 ver

    @Column
    private int order_of_priority;

    @Column
    private String goal_note;

    public int getGoal_id() {
        return goal_id;
    }

    public void setGoal_id(int goal_id) {
        this.goal_id = goal_id;
    }

    public UsersEntity getUser_id() {
        return user_id;
    }

    public void setUser_id(UsersEntity user_id) {
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

    public void setDeadline(java.sql.Date deadline) {
        this.deadline = deadline;
    }

    public String getFree_week_days() {
        return free_week_days;
    }

    public void setFree_week_days(String free_week_days) {
        this.free_week_days = free_week_days;
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

    public int getCalculated_minutes() {
        return calculated_minutes;
    }

    public void setCalculated_minutes(int calculated_minutes) {
        this.calculated_minutes = calculated_minutes;
    }

    public int getComplated_minutes() {
        return complated_minutes;
    }

    public void setComplated_minutes(int complated_minutes) {
        this.complated_minutes = complated_minutes;
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





