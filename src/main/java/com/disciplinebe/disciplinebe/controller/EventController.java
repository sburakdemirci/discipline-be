package com.disciplinebe.disciplinebe.controller;




import com.disciplinebe.disciplinebe.database.entity.EventEntity;
import com.disciplinebe.disciplinebe.database.entity.UsersEntity;
import com.disciplinebe.disciplinebe.database.repository.EventRepository;
import com.disciplinebe.disciplinebe.database.repository.UsersRepository;


import com.disciplinebe.disciplinebe.model.EventModelRequest;
import com.disciplinebe.disciplinebe.service.EventDatabaseService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/event")
public class EventController {


    @Autowired
    EventDatabaseService eventDatabaseService;

    //toDo session yada token verebilirsin.

// todo jwt token en son yaklaşık 4 saatini alır. 1 saat video 3 saat bug vs
    @RequestMapping(method = RequestMethod.POST, value = "/addEvent")
    public boolean addEvent( @RequestBody EventModelRequest eventRequest) {

        return eventDatabaseService.addEvent(eventRequest);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getEventsByUserId")
    public List<EventEntity> getEventByUserId(@RequestParam Integer userId) {
        return eventDatabaseService.getEventByUserId(userId);

    }

    @RequestMapping(method = RequestMethod.GET, value = "/getEventsByDate")
    public List<EventEntity> getEventsByDate(@RequestParam Integer userId, @RequestParam Date date) {
        return eventDatabaseService.getEventsByDate(userId,date);

    }







    // event eklendiği zaman slot controller çalışacal
    //toDo pathvariable ve requestparam arasındaki farklı öğren ve diğer annotations'ları
}
