package com.disciplinebe.disciplinebe.service;

import com.disciplinebe.disciplinebe.database.entity.GoalEntity;
import com.disciplinebe.disciplinebe.database.entity.WorksForGoalEntity;
import com.disciplinebe.disciplinebe.database.repository.GoalRepository;
import com.disciplinebe.disciplinebe.database.repository.WorksForGoalRepository;
import com.disciplinebe.disciplinebe.model.GoalModelRequest;


import com.disciplinebe.disciplinebe.model.TimeSlot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Service
public class GoalDatabaseService {

    @Autowired
    UserService userService;

    @Autowired
    GoalRepository goalRepository;

    @Autowired
    DateConvertService dateConvertService;

    @Autowired
    WorksForGoalRepository worksForGoalRepository;

    public boolean addGoal(GoalModelRequest goalModelRequest){
  //toDo goal eklerken sadece 1 tane eklenebilsin. 1 goal üzerinden git
        List<GoalEntity> goalEntities =getByUserId(goalModelRequest.getUser_id());

        if(goalEntities.size()>0) return false;

        GoalEntity goalEntity=new GoalEntity();

        goalEntity.setUser_id(userService.getUserById(goalModelRequest.getUser_id()));
        if(goalEntity.getUser_id()==null) return false;
        goalEntity.setGoal_name(goalModelRequest.getGoal_name());
        goalEntity.setDeadline(dateConvertService.stringToSqlDate(goalModelRequest.getDeadline().toString()));
        goalEntity.setGoal_note(goalModelRequest.getGoal_note());
        goalEntity.setOrder_of_priority(1);
        goalEntity.setFree_week_days(goalModelRequest.getSelected_week_days());
        goalEntity.setCalculated_minutes(calculateTotalMinutes(goalModelRequest.getWeekly_work_hours(),goalModelRequest.getDeadline()));
        goalEntity.setComplated_minutes(0);

        goalEntity.setTime_zone_finish(goalModelRequest.getTime_zone_finish());
        goalEntity.setWeekly_work_hours(0);


        try {
            goalRepository.save(goalEntity);
            return true;
        }catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }

    public List<GoalEntity> getByUserId(int userId)
    {
        List<GoalEntity> goalEntities = new ArrayList<>();
        goalEntities= goalRepository.findByUid(userId);
        return goalEntities;

    }

    public int weekCountToDeadline( Date deadLine)
    {
        Date today= new Date(Calendar.getInstance().getTime().getTime());

       long numOfWeeks = ChronoUnit.WEEKS.between(today.toLocalDate(),deadLine.toLocalDate());
       int returnValue =(int)numOfWeeks;

        return returnValue;
    }

    public int calculateTotalMinutes(int weeklyHours, Date deadLine)
    {

        weeklyHours*=60;
        return weekCountToDeadline(deadLine)*weeklyHours;
    }

    public List<WorksForGoalEntity> getWorksForGoalByDate(int userId, Date date)
    {
        return worksForGoalRepository.findByDateAndUserId(userId,date);
    }

    // parametreler date,goalid,


//todo addworkforgoal olunca total çalışma zamanından düşülecek.

}
