package com.disciplinebe.disciplinebe.service;

import com.disciplinebe.disciplinebe.database.entity.RoutineEntity;
import com.disciplinebe.disciplinebe.database.repository.RoutineRepository;
import com.disciplinebe.disciplinebe.model.RoutineModelRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;


@Service
public class RoutineDatabaseService {

    @Autowired
    RoutineRepository routineRepository;
    @Autowired
    UserService userService;

    public boolean addRoutine(RoutineModelRequest routineModelRequest)
    {
        RoutineEntity routineEntity = new RoutineEntity();
        routineEntity.setUser_id(userService.getUserById(routineModelRequest.getUser_id()));
        routineEntity.setNotification_enabled(routineModelRequest.isNotification_enabled());
        routineEntity.setNotification_time(routineModelRequest.getNotification_time());
        routineEntity.setRoutine_name(routineModelRequest.getRoutine_name());
        routineEntity.setRoutine_note(routineModelRequest.getRoutine_note());
        routineEntity.setSelected_days(routineModelRequest.getSelected_week_days());
        routineEntity.setTime_finish(routineModelRequest.getTime_finish());
        routineEntity.setTime_start(routineModelRequest.getTime_start());
        try {
            routineRepository.save(routineEntity);
            return true;
        }catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }

    public List<RoutineEntity> findByUserId(int userId)
    {
        List<RoutineEntity> routineEntities = new ArrayList<>();
        routineEntities=routineRepository.findByUid(userId);
        return  routineEntities;
    }
    public List<RoutineEntity> findByUserAndDate(int userId, Date date)
    {
        List<RoutineEntity> routineEntities = new ArrayList<>();
        routineEntities=routineRepository.findByUid(userId);
        return  routineEntities;
    }
    //todo getirme olayını aylık yapabilirsin.

}
// todo  n routine eklediğin zaman çakışma olursa bildirim göndersin. ve kullanıcıdan kontrol etmesini isteyebilir.
//  yada bir işlem yapmayıp aynı saatte 2 tane işleme olabilir


//