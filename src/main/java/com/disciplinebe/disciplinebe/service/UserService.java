package com.disciplinebe.disciplinebe.service;

import com.disciplinebe.disciplinebe.database.entity.UsersEntity;
import com.disciplinebe.disciplinebe.database.repository.UsersRepository;

import com.disciplinebe.disciplinebe.model.UserModelRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.jws.soap.SOAPBinding;
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
        UsersEntity usersEntity = usersRepository.findByMail(email);
        if(usersEntity != null && usersEntity.getEmail().equalsIgnoreCase(email) && usersEntity.getPassword().equals(password))
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



//todo deleteuser servisi yap

}
