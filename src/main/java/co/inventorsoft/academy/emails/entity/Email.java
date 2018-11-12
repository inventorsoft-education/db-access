package co.inventorsoft.academy.emails.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "emails")
@Getter
@Setter
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Email {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false)
	Integer id;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name="to_addr_id")
	Address to;	

	@Column(nullable = false)
	String subject;	

	@Column
	String text;	

	@Column(nullable = false)
	LocalDateTime sentDate;

	public Email(Address to, String subject, String text, LocalDateTime sentDate) {
		this.to = to;
		this.subject = subject;
		this.text = text;
		this.sentDate = sentDate;
	}	

}
