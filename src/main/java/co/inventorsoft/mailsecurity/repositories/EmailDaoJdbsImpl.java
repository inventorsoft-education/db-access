package co.inventorsoft.mailsecurity.repositories;

import co.inventorsoft.mailsecurity.models.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class EmailDaoJdbsImpl implements EmailDao{

    @Autowired
    private final JdbcTemplate jdbcTemplate;

    public EmailDaoJdbsImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private Map<Integer, Email> emailMap = new HashMap<>();


    private RowMapper<Email> emailRowMapper = (ResultSet resultSet, int i) -> {
        Integer id = resultSet.getInt("id");

        if (!emailMap.containsKey(id)) {
            String recipient = resultSet.getString("recipient");
            String subject = resultSet.getString("subject");
            String body = resultSet.getString("body");
            LocalDateTime date = LocalDateTime.parse(resultSet.getString("date"), DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"));
            Email email = new Email(id, recipient, subject, body, date);
            emailMap.put(id, email);
        }
        return emailMap.get(id);
    };

    @Override
    public List<Email> findAll() {
        String SQL_SELECT_ALL = "SELECT * FROM emails";
        return jdbcTemplate.query(SQL_SELECT_ALL, emailRowMapper);
    }

    @Override
    public void saveMail(Email email) {
        String SQL_INSERT = "INSERT INTO emails(recipient,subject, body, date) VALUES (?,?,?,?)";
        jdbcTemplate.update(SQL_INSERT, email.getRecipient(), email.getSubject(), email.getBody(), email.getDate());
    }

    @Override
    public void update(Email email) {
        String SQL_UPDATE = "UPDATE emails SET recipient=?, subject=?, body=?, date=? WHERE id=?";
        jdbcTemplate.update(SQL_UPDATE, email.getRecipient(), email.getSubject(), email.getBody(), email.getDate(), email.getId());
    }

    @Override
    public List<Email> findById(int id) {
        String SQL_FIND_BY_ID = "SELECT * FROM emails WHERE id=?";
        return jdbcTemplate.query(SQL_FIND_BY_ID, emailRowMapper, id);
    }

    @Override
    public void delete(Email email) {
        String SQL_DELETE = "DELETE FROM mails WHERE id=?";
        jdbcTemplate.update(SQL_DELETE, email.getId());
    }

    @Override
    public List<Email> mailsToSend() {
        List<Email> emailList = findAll();
        return emailList.stream()
                .filter(email -> email.getDate().equals(LocalDateTime.now()))
                .collect(Collectors.toList());
    }
}
