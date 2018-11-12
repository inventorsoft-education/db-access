package co.inventorsoft.academy.emails;

import java.time.LocalDateTime;
import java.util.List;

public interface EmailDAO {
	
	List<VerySimpleMail> getAll();
	VerySimpleMail get(int id);
	void add(VerySimpleMail email);
	void add(List<VerySimpleMail> emails);
	void update(int id, LocalDateTime date);
	void delete(int id);
	void clear();

}
