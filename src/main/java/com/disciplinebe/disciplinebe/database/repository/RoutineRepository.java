package com.disciplinebe.disciplinebe.database.repository;



import com.disciplinebe.disciplinebe.database.entity.RoutineEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository


public interface RoutineRepository extends  JpaRepository<RoutineEntity,Integer> {

    @Query(value = "SELECT * FROM user_routine as routines WHERE routines.user_id= ?1", nativeQuery = true)
    List<RoutineEntity> findByUid(int uid);




}

