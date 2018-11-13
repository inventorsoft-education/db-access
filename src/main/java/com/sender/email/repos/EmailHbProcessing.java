package com.sender.email.repos;

import com.sender.email.models.Email;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Repository
@Profile("Hibernate")
public interface EmailHbProcessing extends JpaRepository<Email, Integer>, EmailProcessing{
    @Override
    List<Email> findAll();

    @Override
    <S extends Email> S save(S s);

    @Override
    @Transactional
    @Modifying
    void deleteById(Integer integer);

    @Override
    @Transactional
    @Modifying
    @Query(value = "UPDATE emails SET delivery_date = ?2 WHERE id = ?1", nativeQuery = true)
    void changeDate(int id, Date newDate);

    @Override
    @Query(value = "SELECT * FROM emails WHERE id = ?1", nativeQuery = true)
    void changeStatus(int id);

    @Override
    @Query(value = "SELECT * FROM emails WHERE is_sent = 0", nativeQuery = true)
    List<Email> getUnsent();

    @Override
    default void addNewEmail(List<Email> emails) {
        save(emails.get(0));
    }

    @Override
    default void delete(int index) {
        deleteById(index);
    }

    @Override
    default List<Email> getAll() {
        return findAll();
    }



}
