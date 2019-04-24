package com.disciplinebe.disciplinebe.service;

import com.disciplinebe.disciplinebe.database.entity.EventEntity;
import com.disciplinebe.disciplinebe.database.entity.GoalEntity;
import com.disciplinebe.disciplinebe.database.entity.RoutineEntity;
import com.disciplinebe.disciplinebe.model.TimeSlot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

@Service
public class KaizenService {

    @Autowired
    EventDatabaseService eventDatabaseService;

    @Autowired
    GoalDatabaseService goalDatabaseService;

    @Autowired
    RoutineDatabaseService routineDatabaseService;



    public List<TimeSlot> getOccupiedSlots(int userId, Date date)
    {
        SimpleDateFormat simpleDateformat = new SimpleDateFormat("E"); // the day of the week abbreviated
        String dayFromDate= simpleDateformat.format(date);

        List<TimeSlot> timeSlots = new ArrayList();

        List<EventEntity> eventEntities = eventDatabaseService.getEventsByDate(userId,date);

        List<GoalEntity> goalEntities = goalDatabaseService.getByUserId(userId);
        List<RoutineEntity> routineEntities = routineDatabaseService.findByUserId(userId);

        for(EventEntity eventEntity:eventEntities)
        {
           TimeSlot timeSlot=new TimeSlot();
           timeSlot.setTimeStart(eventEntity.getTime_start());
           timeSlot.setTimeFinish(eventEntity.getTime_finish());
           timeSlots.add(timeSlot);
        }

//        for(GoalEntity goalEntity:goalEntities)
//        {
//            boolean controlIfExists=false;
//            String[] splitted = goalEntity.getFree_week_days().split("/");
//
//            for(String day: splitted)
//            {
//                if(day.equals(dayFromDate)) controlIfExists=true;
//
//            }
//            if(controlIfExists)
//            {
//                TimeSlot timeSlot=new TimeSlot();
//
//            }
//
//            controlIfExists=false;
//
//        }
//
        for (RoutineEntity routineEntity: routineEntities)
        {
            String[] splitted= routineEntity.getSelected_week_days().split("/");
            for(String day: splitted)
            {
                if(day.equals(dayFromDate))
                {
                    TimeSlot timeSlot = new TimeSlot();
                    timeSlot.setTimeStart(routineEntity.getTime_start());
                    timeSlot.setTimeFinish(routineEntity.getTime_finish());
                    timeSlots.add(timeSlot);
                }
            }

        }

    return timeSlots;


    }



    //toDo burada kullanıcının seviyesine göre çalışma programı hazırlanacak.

//  todo  0-10 arası seviye için 10 vs diye gider.
//  todo      burada çalışmayı dakikalara bölüp programı yap, ancak kullanıcı çalışmayı ertelediğinde bunun kaydını nasıl tutacaksın
//   ona bak

    // todo kullanıcılar için takvimlerini nasıl bir araya toplayacaksın bunu düşün. event, goal ve routine'den ayrı ayrı
    //  çekip mi oluşturacaksın , yoksa bir tabloda tutup mu çekeceksin
    // todo kullanıcının disiplin seviyesine göre  günlük 10 dk lık intervallar koy.

    // todo 60 puanlık birinin 60 dk çalışmayı yarıda keserse çalıştıgı dakikayı tutman gerek. başladıgı zamanı log olarak tut.
    //  log tutmayı da öğrenirsin
    // todo yarıda kestikten sonra bir fonksiyon çalışacak ve kalan dakikaları kalan dakikalara, tamamlanmışları ise kalan total
    //  çalışma zamanından düşsün

    //todo etkinlik , rutin ve goal olarak etkinlikleri günlük olarak çekip içeriye basacak bir servis yaz

}
