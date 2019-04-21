package com.disciplinebe.disciplinebe.database.repository;



import com.disciplinebe.disciplinebe.database.entity.QuotesEntity;
import com.disciplinebe.disciplinebe.database.entity.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository


public interface QuotesRepository extends  JpaRepository<QuotesEntity,Integer> {



    @Query("SELECT ue From QuotesEntity ue Where ue.quote_id = ?1")
    QuotesEntity findById(int id);

    @Query(value = "SELECT * FROM events as ee Where ee.user_id = ?1",nativeQuery = true)
    List<QuotesEntity> findAll();






}

