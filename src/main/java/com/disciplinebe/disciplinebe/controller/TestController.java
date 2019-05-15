package com.disciplinebe.disciplinebe.controller;


import com.disciplinebe.disciplinebe.database.entity.EventEntity;
import com.disciplinebe.disciplinebe.database.entity.TestEntity;
import com.disciplinebe.disciplinebe.database.repository.EventRepository;
import com.disciplinebe.disciplinebe.database.repository.TestRepository;
import com.disciplinebe.disciplinebe.database.repository.UsersRepository;
import com.disciplinebe.disciplinebe.service.DisciplineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
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

    @Autowired
    DisciplineService disciplineService;




    //toDo session yada token verebilirsin.
    @RequestMapping(method = RequestMethod.GET, value = "/addQuestion")
    public boolean addQuestion(@RequestParam String questionString,
                               @RequestParam String answer1,
                               @RequestParam String answer2,
                               @RequestParam String answer3,
                               @RequestParam String answer4,
                               @RequestParam String answer5
                               ) {
        return disciplineService.addTestQuestion(questionString,answer1,answer2,answer3,answer4,answer5);
        //todo bunları servise çek burada kod olmasın
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getAllQuestions")
    public List<TestEntity> getAllQuestions() {
        List<TestEntity> tests = testRepository.findAll();
        return tests;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getBymonth")
    public List<EventEntity> get (@RequestParam int userId, int month, int year)
    {

        return eventRepository.findByDateBetween(userId,month,year);

    }



}
