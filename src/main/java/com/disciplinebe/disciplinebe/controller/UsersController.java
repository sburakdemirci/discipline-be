package com.disciplinebe.disciplinebe.controller;

import com.disciplinebe.disciplinebe.database.entity.UsersEntity;
import com.disciplinebe.disciplinebe.database.repository.UsersRepository;
import com.disciplinebe.disciplinebe.model.UserModelRequest;
import com.disciplinebe.disciplinebe.service.DisciplineService;
import com.disciplinebe.disciplinebe.service.GoalDatabaseService;
import com.disciplinebe.disciplinebe.service.UserService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;

@RestController
@RequestMapping("/users")
public class UsersController {
    @Autowired
    UsersRepository usersRepository;

    @Autowired
    UserService userService;

    @Autowired
    GoalDatabaseService disciplineService;

    @RequestMapping(method = RequestMethod.POST, value = "/signUp")
    public boolean addUser(@RequestBody UserModelRequest userModelRequest) {

        return userService.addUser(userModelRequest);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/signIn")
    public boolean login(@RequestParam String email, @RequestParam String password)
    {
        return userService.login(email,password);


    }

    @RequestMapping(method = RequestMethod.GET, value = "/getUserIdByEmail")
    public int getUserIdByEmail(@RequestParam String email)
    {
        return userService.getUserIdByEmail(email);


    }

//toDo kullanıcıya ait belirli günlere göre bütün eventleri getir. bu haftalık veya aylık olabilir.




}


//@RequestParam(required = false) String email yazabilirsin.
//toDo session yada token verebilirsin.
//toDo ilerde asyncstoroge'ye token kaydedip 1 günde birden fazla üyelik almamasını sağlayabilirsin.
//RequestBody kullanılacaksa post kullanılmalı yoksa gönderilen Json 'u göremiyor.
