package com.disciplinebe.disciplinebe.controller;


import com.disciplinebe.disciplinebe.database.entity.EventEntity;
import com.disciplinebe.disciplinebe.database.entity.GoalEntity;
import com.disciplinebe.disciplinebe.database.repository.EventRepository;
import com.disciplinebe.disciplinebe.database.repository.UsersRepository;
import com.disciplinebe.disciplinebe.model.GoalModelRequest;
import com.disciplinebe.disciplinebe.service.GoalDatabaseService;
import com.disciplinebe.disciplinebe.service.KaizenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.ArrayList;
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

    @RequestMapping(method = RequestMethod.GET, value = "/deneme")
    public boolean deneme(@RequestParam int userId, @RequestParam Date date) {


    kaizenService.getOccupiedSlots(userId,date);
    return true;


    }
    //toDo kullanıcıların oluşturulan haftalık goal takvimi için o tabloda flag olacak. her yeni etkinlik
    // eklendiği zaman diğerleri deaktif olacak ve yeniden bir takvim oluşturulacakaAAAAA

    // goal eklendiği zaman da slot controller çalışacak
}
