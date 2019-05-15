package com.disciplinebe.disciplinebe.service;

import com.disciplinebe.disciplinebe.database.entity.TestEntity;
import com.disciplinebe.disciplinebe.database.entity.UsersEntity;
import com.disciplinebe.disciplinebe.database.repository.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DisciplineService {



    @Autowired
    UserService userService;

    @Autowired
    TestRepository testRepository;

//toDo class get testQuestions

//toDo class change test point of user

    public boolean calculetaResult(int userId,int testResult){

        UsersEntity usersEntity = userService.getUserById(userId);
        usersEntity.setDiscipline_level(testResult);

        try {
            userService.updateUser(usersEntity);
            return true;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }
    public boolean addTestQuestion(String question, String answer1, String answer2, String answer3, String answer4 ,String answer5){
        TestEntity testEntity=new TestEntity();
        testEntity.setQuestion_string(question);
        testEntity.setAnswer_1(answer1);
        testEntity.setAnswer_2(answer2);
        testEntity.setAnswer_3(answer3);
        testEntity.setAnswer_4(answer4);
        testEntity.setAnswer_5(answer5);
        try {
            testRepository.save(testEntity);
            return true;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }

    }




    //todo test resultlar direk olarak int olarak gelecek. react dan
    // buraya. daha sonrasında questionların , answers ve userid'lerin tutuldugu bir tabloya yazarsın ve ordan hesaplarsın






    //toDo test yapmak ve puan oluşturmak
}
