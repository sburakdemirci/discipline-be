package com.disciplinebe.disciplinebe.service;

import com.disciplinebe.disciplinebe.database.entity.QuotesEntity;
import com.disciplinebe.disciplinebe.database.repository.QuotesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuotesDatabaseService {

    @Autowired
    QuotesRepository quotesRepository;

    public QuotesEntity getEntityById(int quoteId)
    {
        QuotesEntity quotesEntity= quotesRepository.findById(quoteId);
        return quotesEntity;

    }

    public boolean addQuote(String quote)
    {
        QuotesEntity quotesEntity = new QuotesEntity();
        quotesEntity.setQuote_string(quote);

        try {
            quotesRepository.save(quotesEntity);
            return true;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }
}

//todo söz kullanıcıya gösterildikten sonra controller ile userid param olarak al ve bu classda yazacağın servis , kullanıcının son gösterilen quote'sini güncellesin.  bir arttırıp bidakine geçsin
