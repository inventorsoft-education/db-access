package co.inventorsoft.academy.emails.sql;

import java.time.LocalDateTime;
import java.util.List;
import static java.util.stream.Collectors.toList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import co.inventorsoft.academy.emails.VerySimpleMail;

//@Primary
@Repository
public class EmailJDBC implements EmailSQL {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Transactional(readOnly = true)
	@Override
	public List<VerySimpleMail> getAll() {
		return jdbcTemplate.query(
				"SELECT e.id,a.address,e.subject,e.text,e.sentDate FROM emails e JOIN addresses a ON e.to_addr_id=a.id",
				(rs, rowNum) -> new VerySimpleMail( rs.getInt("id"),
													rs.getString("address"),
													rs.getString("subject"),
													rs.getString("text"),
													rs.getTimestamp("sentDate").toLocalDateTime() ) );
	}

	@Transactional(readOnly = true)
	@Override
	public VerySimpleMail get(int id) {
		try {
			return jdbcTemplate.queryForObject(
					"SELECT e.id,a.address,e.subject,e.text,e.sentDate FROM emails e JOIN addresses a ON e.to_addr_id=a.id WHERE e.id = ?",
					new Object[] { id },
					(rs, rowNum) -> new VerySimpleMail( rs.getInt("id"),
														rs.getString("address"),
														rs.getString("subject"),
														rs.getString("text"),
														rs.getTimestamp("sentDate").toLocalDateTime() ) );
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	@Transactional
	@Override
	public void add(VerySimpleMail email) {
		if( email==null ) {
			return;
		}
		jdbcTemplate.update("INSERT IGNORE INTO addresses (address) VALUES (?)", email.getTo());
		jdbcTemplate.update("INSERT INTO emails (to_addr_id,subject,text,sentDate) VALUES ( (SELECT id FROM addresses WHERE address=?),?,?,?)",
			email.getTo(), email.getSubject(), email.getText(), email.getSentDate());
		email.setId( jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class) );
	}

	@Transactional
	@Override
	public void add(List<VerySimpleMail> emails) {
		if( emails==null || emails.isEmpty() ) {
			return;
		}
		jdbcTemplate.batchUpdate("INSERT IGNORE INTO addresses (address) VALUES (?)", 
			emails.stream().map(VerySimpleMail::getTo).distinct().map(x->new Object[]{x}).collect(toList()));
		for(VerySimpleMail email : emails) {
			jdbcTemplate.update("INSERT INTO emails (to_addr_id,subject,text,sentDate) VALUES ( (SELECT id FROM addresses WHERE address=?),?,?,?)",
				email.getTo(), email.getSubject(), email.getText(), email.getSentDate());
			email.setId( jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class) );
		}
	}

	@Transactional
	@Override
	public void update(int id, LocalDateTime date) {
		if( jdbcTemplate.update("UPDATE emails SET sentDate = ? WHERE id = ?", date, id) == 0 ) {
			throw new IndexOutOfBoundsException("Index does not exist");
		}
	}

	@Transactional
	@Override
	public void delete(int id) {
		if( jdbcTemplate.update("DELETE FROM emails WHERE id = ?", id) == 0 ) {
			throw new IndexOutOfBoundsException("Index does not exist");
		}
	}

	@Transactional
	@Override
	public void clear() {
		jdbcTemplate.execute("TRUNCATE TABLE emails");
	}

}
