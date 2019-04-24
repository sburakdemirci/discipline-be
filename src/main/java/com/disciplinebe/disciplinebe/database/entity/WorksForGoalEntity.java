package com.disciplinebe.disciplinebe.database.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.apache.catalina.User;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.sql.Date;

@Entity
@Table(name = "works_for_goal")
public class WorksForGoalEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private Date date;

    @Column
    private int start_time;

    @Column
    private int duration;

    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "goal_id", nullable = false)
    private GoalEntity goal_id;

    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UsersEntity user_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getStart_time() {
        return start_time;
    }

    public void setStart_time(int start_time) {
        this.start_time = start_time;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public GoalEntity getGoal_id() {
        return goal_id;
    }

    public void setGoal_id(GoalEntity goal_id) {
        this.goal_id = goal_id;
    }
}
