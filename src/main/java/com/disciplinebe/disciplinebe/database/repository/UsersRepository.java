package com.disciplinebe.disciplinebe.database.repository;


import com.disciplinebe.disciplinebe.database.entity.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository



public interface UsersRepository extends JpaRepository<UsersEntity, Integer> {

    @Query("SELECT ue From UsersEntity ue Where ue.id = ?1")
    UsersEntity findByUid(int uid);

    @Query("SELECT ue From UsersEntity ue Where ue.email = ?1")
    UsersEntity findByMail(String email);


}
