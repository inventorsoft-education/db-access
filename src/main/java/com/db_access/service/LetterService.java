package com.db_access.service;

import com.db_access.dao.LetterDaoImpl;
import com.db_access.entity.Letter;
import com.db_access.exceptions.NoLetterWithThatId;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class LetterService {

    private LetterDaoImpl letterDao;

    public LetterService(LetterDaoImpl letterDao) {
        this.letterDao = letterDao;
    }

    public Letter getById(Long id) {
        return letterDao.findById(id).orElseThrow(() -> {
            throw new NoLetterWithThatId("There is no letter with that id =" + id);
        });
    }

    public List<Letter> getByRecipient(String recipient) {
        return letterDao.findByRecipient(recipient);
    }

    public List<Letter> getAll() {
        return letterDao.findAll();
    }

    public void save(Letter letter) {
        letterDao.save(letter);
    }

    public void update(Letter letter) {
        letterDao.save(letter);
    }


    public void deleteById(Long id) {
        letterDao.deleteById(id);
    }
}
