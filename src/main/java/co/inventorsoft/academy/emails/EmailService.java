package co.inventorsoft.academy.emails;

import java.time.ZoneId;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledFuture;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

	private JavaMailSender emailSender;
	private Logger log;
	private EmailDAO emailDAO;
	private ThreadPoolTaskScheduler scheduler;
	
	private Map<VerySimpleMail, ScheduledFuture<?>> futures = new ConcurrentHashMap<>();
	
	@Autowired
	public EmailService(JavaMailSender emailSender, Logger log, EmailDAO emailDAO, ThreadPoolTaskScheduler scheduler) {
		this.emailSender = emailSender;
		this.log = log;
		this.emailDAO = emailDAO;
		this.scheduler = scheduler;
	}

	public void sendFutureEmail() {
		for(VerySimpleMail email : emailDAO.getAll()) {
			log.info(" E-mail  To:{}  Subject:{}  Date:{} ",email.getTo(),email.getSubject(),email.getSentDate());
			futures.put(email, sendOneEmail(email));
		}
		loggingState(); 
	}

	public void sendNewEmail(VerySimpleMail email) {
		log.info(" New E-mail  To:{}  Subject:{}  Date:{} ",email.getTo(),email.getSubject(),email.getSentDate());
		futures.put(email, sendOneEmail(email));
		loggingState(); 
	}

	public void sendEmailNewDate(VerySimpleMail email) {
		log.warn(" New Date for E-mail  To:{}  Subject:{}  Date:{} ",email.getTo(),email.getSubject(),email.getSentDate());
		futures.get(email).cancel(true);
		futures.put(email, sendOneEmail(email));
		loggingState(); 
	}

	public void cancelEmail(VerySimpleMail email) {
		log.warn(" Delete E-mail  To:{}  Subject:{}  Date:{} ",email.getTo(),email.getSubject(),email.getSentDate());
		futures.get(email).cancel(true);
		futures.remove(email);
		loggingState(); 
	}
	
	public void clear() {
		log.warn(" CLEAR ALL E-mails !!! ");
		for(VerySimpleMail email : futures.keySet()) {
			futures.get(email).cancel(true);
			futures.remove(email);
		}
		loggingState(); 
	}
	
	private ScheduledFuture<?> sendOneEmail(VerySimpleMail email) {
		return scheduler.schedule( () -> 
				{
					try{
						log.info("\n Sending E-mail \n To: {} \n Subject: {} \n Date: {} ",email.getTo(),email.getSubject(),email.getSentDate());
						emailSender.send(email.toSimpleMailMessage());
						futures.remove(email);
						emailDAO.delete(email.getId());
					} catch(Exception e) {
						log.warn(" E-mail  To:{}  Subject:{}  Date:{} ",email.getTo(),email.getSubject(),email.getSentDate());
						log.error(e.getMessage());
					}
				},
				Date.from(email.getSentDate().atZone(ZoneId.systemDefault()).toInstant()) );
	}

	private void loggingSchedulerState() {
		log.info(" State  {}:{} ",scheduler.getThreadNamePrefix(),scheduler.getScheduledThreadPoolExecutor()); 
	}

	private void loggingFuturesState() {
		log.info(" State  ScheduledFuture:{} ",futures.size()); 
	}

	private void loggingState() {
		loggingSchedulerState(); 
		loggingFuturesState();
	}

}
