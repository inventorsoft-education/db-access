package co.inventorsoft.academy.emails;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import org.springframework.mail.SimpleMailMessage;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class VerySimpleMail implements Serializable {
	private static final long serialVersionUID = 1L;

	int id;
	@NotNull
	@Email
	String to;
	@NotNull
	String subject;
	@NotNull
	String text;
	@NotNull
	LocalDateTime sentDate;
	
	public VerySimpleMail(int id, SimpleMailMessage email) {
		this.id = id;
		this.to = (email.getTo() != null) ? email.getTo()[0] : ""; 
		this.subject = email.getSubject(); 
		this.text = email.getText();
		this.sentDate = LocalDateTime.ofInstant(email.getSentDate().toInstant(), ZoneId.systemDefault());
	}
	
	public VerySimpleMail(co.inventorsoft.academy.emails.entity.Email email) {
		this.id = email.getId();
		this.to = email.getTo().getAddress(); 
		this.subject = email.getSubject(); 
		this.text = email.getText();
		this.sentDate = email.getSentDate();
	}
	
	public SimpleMailMessage toSimpleMailMessage() {
		SimpleMailMessage email = new SimpleMailMessage();
		email.setTo(this.getTo()); 
		email.setSubject(this.getSubject()); 
		email.setText(this.getText());
		email.setSentDate( Date.from(this.getSentDate().atZone(ZoneId.systemDefault()).toInstant()) );
		return email;
	}

	@Override
	public int hashCode() {
		return id;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if ( (obj == null) || !(obj instanceof VerySimpleMail) ) {
			return false;
		}
		VerySimpleMail other = (VerySimpleMail) obj;
		if (id != other.id) {
			return false;
		}
		return true;
	}

}
