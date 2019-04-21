package com.disciplinebe.disciplinebe.controller;


import com.disciplinebe.disciplinebe.database.entity.EventEntity;
import com.disciplinebe.disciplinebe.database.entity.QuotesEntity;
import com.disciplinebe.disciplinebe.database.repository.EventRepository;
import com.disciplinebe.disciplinebe.database.repository.UsersRepository;
import com.disciplinebe.disciplinebe.service.QuotesDatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/quotes")
public class QuotesController {

    @Autowired
    QuotesDatabaseService quotesDatabaseService;


    //toDo session yada token verebilirsin.
    @RequestMapping(method = RequestMethod.GET, value = "/addQuote")
    public boolean addQuote(@RequestParam String qoute) {

        return quotesDatabaseService.addQuote(qoute);

    }

    @RequestMapping(method = RequestMethod.GET, value = "/getQuoteById")
    public String getQuoteById(@RequestParam Integer quoteId) {

        return quotesDatabaseService.getEntityById(quoteId).getQuote_string();



    }
    // instagramdan yada disiplin sözleri yazarak yaklaşık 100 tane söz ekle. pop up pencere olarak gösterebilirsin.
}
