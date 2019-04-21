package com.disciplinebe.disciplinebe.controller;


import com.disciplinebe.disciplinebe.database.entity.EventEntity;
import com.disciplinebe.disciplinebe.database.entity.TestEntity;
import com.disciplinebe.disciplinebe.database.repository.EventRepository;
import com.disciplinebe.disciplinebe.database.repository.TestRepository;
import com.disciplinebe.disciplinebe.database.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    EventRepository eventRepository;

    @Autowired
    UsersRepository usersRepository;

    @Autowired
    TestRepository testRepository;

    //toDo session yada token verebilirsin.
    @RequestMapping(method = RequestMethod.GET, value = "/addQuestion")
    public boolean addQuestion(@RequestParam Integer userId, @RequestParam String eventName) {
        try {
            EventEntity eventEntity = new EventEntity();
            eventEntity.setEvent_name(eventName);
            eventEntity.setUser_id(usersRepository.findByUid(userId));
            eventRepository.save((eventEntity));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        //todo bunları servise çek burada kod olmasın
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getAllQuestions")
    public List<TestEntity> getAllQuestions() {
        List<TestEntity> tests = testRepository.findAll();
        return tests;
    }
}
