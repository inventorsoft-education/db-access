package com.db_access.service;

import com.db_access.dao.BaseDao;
import com.db_access.entity.Letter;
import com.db_access.exceptions.NoLetterWithThatId;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LetterService {

    private BaseDao<Letter> letterDao;

    public LetterService(BaseDao<Letter> letterDao) {
        this.letterDao = letterDao;
    }

    public Letter getById(Long id) {
        return letterDao.getById(id).orElseThrow(() -> {
            throw new NoLetterWithThatId("There is no letter with that id =" + id);
        });
    }

    public List<Letter> getByRecipient(String recipient) {
        return letterDao.getByRecipient(recipient);
    }

    public List<Letter> getAll() {
        return letterDao.getAll();
    }

    public void save(Letter letter) {
        letterDao.save(letter);
    }

    public void updateById(Letter letter) {
        letterDao.updateById(letter);
    }


    public void deleteById(Long id) {
        letterDao.deleteById(id);
    }
}
