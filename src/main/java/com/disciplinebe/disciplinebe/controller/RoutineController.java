package com.disciplinebe.disciplinebe.controller;


import com.disciplinebe.disciplinebe.database.entity.EventEntity;
import com.disciplinebe.disciplinebe.database.entity.RoutineEntity;
import com.disciplinebe.disciplinebe.database.repository.EventRepository;
import com.disciplinebe.disciplinebe.database.repository.UsersRepository;
import com.disciplinebe.disciplinebe.model.RoutineModelRequest;
import com.disciplinebe.disciplinebe.service.RoutineDatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.PreUpdate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/routines")
public class RoutineController {

    @Autowired
    RoutineDatabaseService routineDatabaseService;

    //toDo session yada token verebilirsin.
    @RequestMapping(method = RequestMethod.POST, value = "/addRoutine")
    public boolean addRoutine(@RequestBody RoutineModelRequest routineModelRequest) {
            return routineDatabaseService.addRoutine(routineModelRequest);

    }

    @RequestMapping(method = RequestMethod.GET, value = "/getRoutinesByUserId")
    public List<RoutineEntity> getRoutinesByUserId(@RequestParam Integer userId) {
        return routineDatabaseService.findByUserId(userId);

    }
}
//todo günleri eklerken dropdown checkbox'lar kullan