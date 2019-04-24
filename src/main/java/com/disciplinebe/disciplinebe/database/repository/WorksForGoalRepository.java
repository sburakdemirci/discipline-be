package com.disciplinebe.disciplinebe.database.repository;

import com.disciplinebe.disciplinebe.database.entity.WorksForGoalEntity;
import com.sun.tools.javac.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Date;

public interface WorksForGoalRepository extends JpaRepository<WorksForGoalEntity,Integer> {

    @Query(value = "SELECT * FROM works_for_goal as goal WHERE goal.user_id= ?1 AND goal.date = ?2", nativeQuery = true)
    List<WorksForGoalEntity> findByDateAndUserId(int userId, Date date);

}
