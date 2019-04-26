package com.disciplinebe.disciplinebe.controller;


import com.disciplinebe.disciplinebe.database.entity.EventEntity;
import com.disciplinebe.disciplinebe.database.entity.GoalEntity;
import com.disciplinebe.disciplinebe.database.repository.EventRepository;
import com.disciplinebe.disciplinebe.database.repository.UsersRepository;
import com.disciplinebe.disciplinebe.model.GoalModelRequest;
import com.disciplinebe.disciplinebe.model.TimeSlot;
import com.disciplinebe.disciplinebe.service.GoalDatabaseService;
import com.disciplinebe.disciplinebe.service.KaizenService;
import org.hibernate.validator.constraints.pl.REGON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/goals")
public class GoalController {

    @Autowired
    EventRepository eventRepository;

    @Autowired
    UsersRepository usersRepository;

    @Autowired
    GoalDatabaseService goalDatabaseService;

    @Autowired
    KaizenService kaizenService;

    //toDo session yada token verebilirsin.

    @RequestMapping(method = RequestMethod.POST, value = "/addGoal")
    public boolean addGoal(@RequestBody GoalModelRequest goalModelRequest) {

        return goalDatabaseService.addGoal(goalModelRequest);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getGoalsByUserId")
    public List<GoalEntity> getGoalByUserId(@RequestParam Integer userId) {
     return goalDatabaseService.getByUserId(userId);

    }
    @RequestMapping(method = RequestMethod.GET, value = "/checks")
    public List<TimeSlot> gett(
            @RequestParam Integer userId,
            @RequestParam Date date,
            @RequestParam Integer slotStart,
            @RequestParam Integer slotFinish)
    {

        return kaizenService.getEmptySlotsByDate(userId,date,slotStart,slotFinish);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/addWorkForGoal")
    public boolean addWorkForGoal(@RequestParam Integer goalId, @RequestParam Date date)
    {
        return kaizenService.createWorkForSingleGoal(goalId,date);
    }

}

//toDo kullanıcıların oluşturulan haftalık goal takvimi için o tabloda flag olacak. her yeni etkinlik
// eklendiği zaman diğerleri deaktif olacak ve yeniden bir takvim oluşturulacakaAAAAA

// goal eklendiği zaman da slot controller çalışacak
