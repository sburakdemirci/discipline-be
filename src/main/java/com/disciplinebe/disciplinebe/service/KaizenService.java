package com.disciplinebe.disciplinebe.service;

import com.disciplinebe.disciplinebe.database.entity.*;
import com.disciplinebe.disciplinebe.database.repository.GoalRepository;
import com.disciplinebe.disciplinebe.database.repository.WorksForGoalRepository;
import com.disciplinebe.disciplinebe.model.EventGoalRoutineModel;
import com.disciplinebe.disciplinebe.model.TimeSlot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.*;

@Service
public class KaizenService {

    @Autowired
    EventDatabaseService eventDatabaseService;

    @Autowired
    GoalDatabaseService goalDatabaseService;

    @Autowired
    RoutineDatabaseService routineDatabaseService;

    @Autowired
    WorksForGoalRepository worksForGoalRepository;

    @Autowired
    GoalRepository goalRepository;



    public List<TimeSlot> getOccupiedSlots(int userId, Date date)
    {
        SimpleDateFormat simpleDateformat = new SimpleDateFormat("E"); // the day of the week abbreviated
        String dayFromDate= simpleDateformat.format(date);

        List<TimeSlot> timeSlots = new ArrayList();

        List<EventEntity> eventEntities = eventDatabaseService.getEventsByDate(userId,date);

        List<WorksForGoalEntity> worksForGoalEntities = goalDatabaseService.getWorksForGoalByDate(userId,date);
        List<RoutineEntity> routineEntities = routineDatabaseService.findByUserId(userId);

        for(EventEntity eventEntity:eventEntities)
        {
           TimeSlot timeSlot=new TimeSlot();
           timeSlot.setTimeStart(eventEntity.getTime_start());
           timeSlot.setTimeFinish(eventEntity.getTime_finish());
           timeSlots.add(timeSlot);
        }

        for(WorksForGoalEntity worksForGoalEntity:worksForGoalEntities)

        {
            TimeSlot timeSlot = new TimeSlot();
            timeSlot.setTimeStart(worksForGoalEntity.getStart_time());
            timeSlot.setTimeFinish(worksForGoalEntity.getStart_time()+worksForGoalEntity.getDuration());
            timeSlots.add(timeSlot);
        }

        for (RoutineEntity routineEntity: routineEntities)
        {
            String[] splitted= routineEntity.getSelected_week_days().split(",");
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

    public List<TimeSlot> getEmptySlotsByDate (int userId, Date date,int slotStart,int slotFinish)
    {
        boolean checkOccupied=false;
        List<TimeSlot> occupiedSlots = getOccupiedSlots(userId,date);
        List<TimeSlot> emptySlots = new ArrayList<>();
        int tmpStart=0;
        int tmpFinish=0;
        int lastFinished=0;

        for(int i =slotStart; i<=slotFinish; i++)
        {

            for(TimeSlot timeSlot:occupiedSlots)
            {
                if(i>lastFinished && i>slotStart) break;
                if(lastFinished<timeSlot.getTimeFinish())
                {
                    lastFinished= timeSlot.getTimeFinish();
                }
                if( i>=timeSlot.getTimeStart() && i<= timeSlot.getTimeFinish())
                {

                    checkOccupied=true;
                }
            }
            if(i>lastFinished && i>slotStart)
            {
                TimeSlot timeSlot= new TimeSlot();
                timeSlot.setTimeStart(i);
                timeSlot.setTimeFinish(slotFinish);
                emptySlots.add(timeSlot);
                return emptySlots;
            }

            if(!checkOccupied)
            {
                if(tmpStart>0)
                {
                    tmpFinish++;
                }
                else
                {
                    tmpStart=i;
                    tmpFinish=i;
                }
            }
            else
                if((checkOccupied && tmpStart>0) || i>(slotFinish-1))
                {
                    TimeSlot timeSlot= new TimeSlot();
                    timeSlot.setTimeStart(tmpStart);
                    timeSlot.setTimeFinish(tmpFinish);
                    emptySlots.add(timeSlot);

                    tmpStart=0;
                    tmpFinish=0;
                }

            checkOccupied=false;
        }
        return emptySlots;

    }
    public boolean reduceTotalWorkMinutes(UsersEntity user, int duration)
    {
        List<GoalEntity> goalEntities = goalDatabaseService.getByUserId(user.getId());
        if(goalEntities.size()>0)
        {
            goalEntities.get(0).setComplated_minutes(goalEntities.get(0).getComplated_minutes()+duration);
            try {
                goalRepository.save(goalEntities.get(0));
                return true;
            }
            catch (Exception e)
            {
                e.printStackTrace();
                return false;
            }

        }
        return false;
    }



    public boolean createWorkForSingleGoal(int userId, Date date )
    {
        List<GoalEntity> goalEntities = goalDatabaseService.getByUserId(userId);
        int disciplineLevel=0;
        int slotStart=0;
        int slotFinish=0;
        boolean checkForSizeEnought=false;

        if(goalEntities.size()>0)
        {
            disciplineLevel=goalEntities.get(0).getUser_id().getDiscipline_level();
            slotStart=goalEntities.get(0).getTime_zone_starts();
            slotFinish=goalEntities.get(0).getTime_zone_finish();

            List<TimeSlot> timeSlots= getEmptySlotsByDate(userId,date,slotStart,slotFinish);

            for(TimeSlot timeSlot:timeSlots)
            {
                if((timeSlot.getTimeFinish()-timeSlot.getTimeStart()) >= (disciplineLevel))
                {
                    checkForSizeEnought=true;
                    break;
                }

            }
            if(checkForSizeEnought)
            {
                WorksForGoalEntity worksForGoalEntity = new WorksForGoalEntity();
                worksForGoalEntity.setDate(date);
                worksForGoalEntity.setDuration(disciplineLevel);
                worksForGoalEntity.setGoal_id(goalEntities.get(0));
                worksForGoalEntity.setStart_time(slotStart);
                worksForGoalEntity.setUser_id(goalEntities.get(0).getUser_id());
                try {
                    worksForGoalRepository.save(worksForGoalEntity);

                    return reduceTotalWorkMinutes(goalEntities.get(0).getUser_id(),disciplineLevel);
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }

            }
            else
            {
                int timeleft = disciplineLevel;
                for(TimeSlot timeSlot:timeSlots)
                {
                    if(timeleft>0)
                    {
                        if(timeleft<=(timeSlot.getTimeFinish()-timeSlot.getTimeStart()))
                        {
                            WorksForGoalEntity worksForGoalEntity= new WorksForGoalEntity();
                            worksForGoalEntity.setStart_time(timeSlot.getTimeStart());
                            worksForGoalEntity.setDuration(timeleft);
                            worksForGoalEntity.setDate(date);
                            worksForGoalEntity.setGoal_id(goalEntities.get(0));
                            worksForGoalEntity.setUser_id(goalEntities.get(0).getUser_id());
                            worksForGoalRepository.save(worksForGoalEntity);
                            return reduceTotalWorkMinutes(goalEntities.get(0).getUser_id(),disciplineLevel);
                        }
                        else
                        {
                            WorksForGoalEntity worksForGoalEntity= new WorksForGoalEntity();
                            worksForGoalEntity.setStart_time(timeSlot.getTimeStart());
                            worksForGoalEntity.setDuration((timeSlot.getTimeFinish()-timeSlot.getTimeStart()));
                            worksForGoalEntity.setDate(date);
                            worksForGoalEntity.setGoal_id(goalEntities.get(0));
                            worksForGoalEntity.setUser_id(goalEntities.get(0).getUser_id());
                            timeleft-=worksForGoalEntity.getDuration();
                            worksForGoalRepository.save(worksForGoalEntity);

                        }

                    }

                }

            }
        }

        return false;
    }
//todo aylara göre gönderebilirsin. bir model yaparsın int month, int year ve list<EventGoalRoutine> olur. kıps

    public List<EventGoalRoutineModel> findEventGoalRoutineByMonth(int userId, int month, int year)
    {

//        SimpleDateFormat simpleDateformat = new SimpleDateFormat("E"); // the day of the week abbreviated
//        String dayFromDate= simpleDateformat.format(date);
        List<EventGoalRoutineModel> eventGoalRoutineModels = new ArrayList<>();

        List<WorksForGoalEntity> worksForGoalEntities = goalDatabaseService.getWorksByDateBetween(userId,month,year);
         List<EventEntity> eventEntities = eventDatabaseService.getEventsByDateBetween(userId,month,year);
        List<RoutineEntity> routineEntities= routineDatabaseService.findByUserId(userId);

        for(WorksForGoalEntity worksForGoalEntity:worksForGoalEntities)
        {
            EventGoalRoutineModel eventGoalRoutineModel = new EventGoalRoutineModel();
            eventGoalRoutineModel.setDate(worksForGoalEntity.getDate());
            eventGoalRoutineModel.setDuration(worksForGoalEntity.getDuration());
            eventGoalRoutineModel.setId(worksForGoalEntity.getId());
            eventGoalRoutineModel.setName(worksForGoalEntity.getGoal_id().getGoal_name());
            eventGoalRoutineModel.setStartSlotTime(worksForGoalEntity.getStart_time());
            eventGoalRoutineModel.setType("g");
            eventGoalRoutineModels.add(eventGoalRoutineModel);

        }
        for(EventEntity eventEntity:eventEntities)
        {
            EventGoalRoutineModel eventGoalRoutineModel = new EventGoalRoutineModel();
            eventGoalRoutineModel.setDate(eventEntity.getEvent_date());
            eventGoalRoutineModel.setDuration(eventEntity.getTime_finish()-eventEntity.getTime_start());
            eventGoalRoutineModel.setId(eventEntity.getEvent_id());
            eventGoalRoutineModel.setName(eventEntity.getEvent_name());
            eventGoalRoutineModel.setStartSlotTime(eventEntity.getTime_start());
            eventGoalRoutineModel.setType("e");
            eventGoalRoutineModels.add(eventGoalRoutineModel);
        }

//        if(routineEntities.size()>0 )
//        {
//            String[] splitted= routineEntities.get(0).getSelected_week_days().split(",");
//
//            for(String split: splitted)
//            {
//                if(dayFromDate.equalsIgnoreCase(split))
//                {
//
//                    EventGoalRoutineModel eventGoalRoutineModel = new EventGoalRoutineModel();
//                    eventGoalRoutineModel.setDate(date);
//                    eventGoalRoutineModel.setDuration(routineEntities.get(0).getTime_finish()-routineEntities.get(0).getTime_start());
//                    eventGoalRoutineModel.setId(routineEntities.get(0).getRoutine_id());
//                    eventGoalRoutineModel.setName(routineEntities.get(0).getRoutine_name());
//                    eventGoalRoutineModel.setStartSlotTime(routineEntities.get(0).getTime_start());
//                    eventGoalRoutineModel.setType("r");
//                    eventGoalRoutineModels.add(eventGoalRoutineModel);
//                }
//            }
//
//
//
//        }

        Collections.sort(eventGoalRoutineModels, (o1, o2) -> o1.getDate().compareTo(o2.getDate()));

        return eventGoalRoutineModels;



    }


    //Todo selected days split yapan fonksiyon





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
