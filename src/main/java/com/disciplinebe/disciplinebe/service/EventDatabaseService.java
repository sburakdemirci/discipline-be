package com.disciplinebe.disciplinebe.service;

import com.disciplinebe.disciplinebe.database.entity.EventEntity;
import com.disciplinebe.disciplinebe.database.repository.EventRepository;
import com.disciplinebe.disciplinebe.model.EventModelRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Service
public class EventDatabaseService {

    @Autowired
    EventRepository eventRepository;

    @Autowired
    UserService userService;

    @Autowired
    DateConvertService dateConvertService;


    public boolean addEvent(EventModelRequest eventModelRequest)
    {
        EventEntity eventEntity= new EventEntity();

       eventEntity.setUser_id(userService.getUserById(eventModelRequest.getUser_id()));
       eventEntity.setEvent_name(eventModelRequest.getEvent_name());
       eventEntity.setEvent_date(dateConvertService.stringToSqlDate(eventModelRequest.getEvent_date().toString()));
       eventEntity.setEvent_note(eventModelRequest.getEvent_note());
       eventEntity.setNotification_enabled(eventModelRequest.isNotification_enabled());
       eventEntity.setNotification_time(eventModelRequest.getNotification_time());
       eventEntity.setTime_finish(eventModelRequest.getTime_finish());
       eventEntity.setTime_start(eventModelRequest.getTime_start());

       try {
           eventRepository.save(eventEntity);
           return true;
       }
       catch (Exception e)
       {
           e.printStackTrace();
           return false;
       }



    }

    public List<EventEntity> getEventByUserId(int userId) {
        List<EventEntity> list = new ArrayList<>();
         list=eventRepository.findByUid(userId);
        System.out.println(list);
        return list;
    }
    public List<EventEntity> getEventsByDate(int userId, Date date) {
        List<EventEntity> list = new ArrayList<>();
        list=eventRepository.findByDate(userId,date);
        System.out.println(list);
        return list;
    }




// todo


}
