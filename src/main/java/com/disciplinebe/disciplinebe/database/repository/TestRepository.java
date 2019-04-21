package com.disciplinebe.disciplinebe.database.repository;


import com.disciplinebe.disciplinebe.database.entity.EventEntity;

import com.disciplinebe.disciplinebe.database.entity.TestEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository


public interface TestRepository extends  JpaRepository<TestEntity,Integer> {






}

