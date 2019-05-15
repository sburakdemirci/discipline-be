package com.disciplinebe.disciplinebe.service;

import com.disciplinebe.disciplinebe.database.entity.*;
import com.disciplinebe.disciplinebe.database.repository.EventRepository;
import com.disciplinebe.disciplinebe.database.repository.UsersRepository;

import com.disciplinebe.disciplinebe.model.EventGoalRoutineModel;
import com.disciplinebe.disciplinebe.model.UserModelRequest;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.stereotype.Service;



import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    UsersRepository usersRepository;

    @Autowired
    QuotesDatabaseService quotesDatabaseService;






    public UsersEntity getUserById(int userId)
    {
        UsersEntity usersEntity= new UsersEntity();
        usersEntity=usersRepository.findByUid(userId);
        return usersEntity;
    }




    public List<UsersEntity> getAllUsers()
    {
        List<UsersEntity> usersEntities = new ArrayList<>();
        usersEntities=usersRepository.findAll();
        return usersEntities;
    }




    public boolean addUser(UserModelRequest userModelRequest)
    {
        if(!checkIfEmailExists(userModelRequest.getEmail()))
        {
            return false;

        }
        UsersEntity usersEntity=new UsersEntity();

        usersEntity.setPassword(userModelRequest.getPassword());
        usersEntity.setEmail(userModelRequest.getEmail());
        usersEntity.setName(userModelRequest.getName());
        usersEntity.setBirth_date(userModelRequest.getBirth_date());
        usersEntity.setJob(userModelRequest.getJob());

        usersEntity.setQuote_id(quotesDatabaseService.getEntityById(1));
        usersEntity.setDiscipline_level(0);

        try {
            usersRepository.save(usersEntity);
            return true;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }

        //toDo Discipline Level all

    }

    public boolean login(String email, String password)
    {
        List <UsersEntity> usersEntity = usersRepository.findByMail(email);
        if(usersEntity.size()>0 && usersEntity.get(0).getEmail().equalsIgnoreCase(email) && usersEntity.get(0).getPassword().equals(password))
        {
            return true;
        }
        return false;
    }

    public boolean updateUser(UsersEntity usersEntity)
    {
        try {
            usersRepository.save(usersEntity);
            return true;

        }catch (Exception e)
        {
            return  false;
        }

    }

    public boolean checkIfEmailExists(String email)
    {
        List<UsersEntity> user= usersRepository.findByMail(email);
        if(user!=null)
        {
            return true;
        }
        return false;
    }

    public int getUserIdByEmail(String email)
    {
        List<UsersEntity> list = new ArrayList<>();
         list = usersRepository.findByMail(email);
        if(list==null)
        {
            return 0;
        }
        else {
            return list.get(0).getId();
        }
    }
    public boolean changeUserDisciplineLevel(int userId, int disciplineLevel){
        UsersEntity usersEntity=getUserById(userId);
        usersEntity.setDiscipline_level(disciplineLevel);

        try {
            usersRepository.save(usersEntity);
            return true;
        }catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }



//todo deleteuser servisi yap

}
