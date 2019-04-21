package com.disciplinebe.disciplinebe.database.repository;


import com.disciplinebe.disciplinebe.database.entity.EventEntity;

import com.disciplinebe.disciplinebe.database.entity.GoalEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository


public interface GoalRepository extends  JpaRepository<GoalEntity,Integer> {

    @Query(value = "SELECT * FROM user_goal as goals WHERE goals.user_id= ?1", nativeQuery = true)
    List<GoalEntity> findByUid(int uid);







}

