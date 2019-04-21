package com.disciplinebe.disciplinebe.controller;


import com.disciplinebe.disciplinebe.database.entity.EventEntity;
import com.disciplinebe.disciplinebe.database.entity.GoalEntity;
import com.disciplinebe.disciplinebe.database.repository.EventRepository;
import com.disciplinebe.disciplinebe.database.repository.UsersRepository;
import com.disciplinebe.disciplinebe.model.GoalModelRequest;
import com.disciplinebe.disciplinebe.service.GoalDatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    //toDo session yada token verebilirsin.

    @RequestMapping(method = RequestMethod.POST, value = "/addGoal")
    public boolean addGoal(@RequestBody GoalModelRequest goalModelRequest) {

        return goalDatabaseService.addGoal(goalModelRequest);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getGoalsByUserId")
    public List<GoalEntity> getGoalByUserId(@RequestParam Integer userId) {
     return goalDatabaseService.getByUserId(userId);


    }

    // goal eklendiği zaman da slot controller çalışacak
}
