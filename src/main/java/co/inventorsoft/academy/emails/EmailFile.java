package co.inventorsoft.academy.emails;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Repository;

//@Primary
@Repository
public class EmailFile implements EmailDAO {
	
	private String file;
	private Map<Integer,VerySimpleMail> emails;
	private int nextId;
	
	@SuppressWarnings("unchecked")
	public EmailFile(@Value("${email.file}") String file) {
		this.file = file;
		emails = new ConcurrentHashMap<Integer,VerySimpleMail>();
		if(Files.exists(Paths.get(file))) {
			try(FileInputStream fis = new FileInputStream(file); 
				ObjectInputStream ois = new ObjectInputStream(fis)) {
						emails = (ConcurrentHashMap<Integer,VerySimpleMail>) ois.readObject();
			} catch (IOException | ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		nextId = 1;
	}

	@Override
	public List<VerySimpleMail> getAll() {
		return emails.values().stream().collect(Collectors.toList());
	}
	
	@Override
	public VerySimpleMail get(int id) {
		return emails.get(id);
	}
	
	@Override
	public void add(VerySimpleMail email) {
		while (emails.containsKey(nextId))
			if (++nextId <= 0) {
				nextId = 1;
			}
		email.setId(nextId);
		emails.put(nextId, email);
	}
	
	@Override
	public void add(List<VerySimpleMail> emails) {
		emails.stream().forEach(this::add);
	}

	@Override
	public void update(int id, LocalDateTime date) {
		if ( emails.containsKey(id) ) {
	    	emails.get(id).setSentDate(date);
		} else {
			throw new IndexOutOfBoundsException("Index does not exist");
		}
	}

	@Override
	public void delete(int id) {
		if ( emails.containsKey(id) ) {
			emails.remove(id);
		} else {
			throw new IndexOutOfBoundsException("Index does not exist");
		}
	}
	
	@Override
	public void clear() {
		emails.clear();
	}
	
	@PreDestroy
	@Scheduled(cron = "0 0/15 * * * ?")
	void save() {
		try(FileOutputStream fos = new FileOutputStream(file); 
			ObjectOutputStream oos = new ObjectOutputStream(fos)) {
					oos.writeObject(emails);
		} catch (IOException e) {
			e.printStackTrace();
			deleteEmailFile();
		}
	}

	private void deleteEmailFile() {
		try {
			Files.deleteIfExists(Paths.get(file));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
