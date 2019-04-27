package com.disciplinebe.disciplinebe.service;

import org.springframework.stereotype.Service;

import java.sql.Date;
import java.text.SimpleDateFormat;

@Service
public class DateConvertService {

    public Date stringToSqlDate(String dateString)
    {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
       java.util.Date date=new java.util.Date();
        try {
           date = sdf.parse(dateString);
            java.sql.Date dateSql= new java.sql.Date(date.getTime());
            return dateSql;
        }catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }


    }


}


//toDo buranın adını date time services olarak değiştirip goaldb service'deki saat date fonksiyonlarını buraya çekebilirsin..