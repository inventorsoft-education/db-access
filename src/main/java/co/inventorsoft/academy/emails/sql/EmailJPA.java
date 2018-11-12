package co.inventorsoft.academy.emails.sql;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

import static java.util.stream.Collectors.toList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.inventorsoft.academy.emails.VerySimpleMail;
import co.inventorsoft.academy.emails.entity.Address;
import co.inventorsoft.academy.emails.entity.Email;

@Primary
@Service
public class EmailJPA implements EmailSQL {
	
	@Autowired
	private EmailRepository emailRepository;
	@Autowired
	private AddressRepository addressRepository;

	@Transactional(readOnly = true)
	@Override
	public List<VerySimpleMail> getAll() {
		return emailRepository.findAll().stream().map(VerySimpleMail::new).collect(toList());
	}

	@Transactional(readOnly = true)
	@Override
	public VerySimpleMail get(int id) {
		try {
			return new VerySimpleMail( emailRepository.findById(id).get() );
		} catch (NoSuchElementException e) {
			return null;
		}
	}

	@Transactional
	@Override
	public void add(VerySimpleMail email) {
		if( email==null ) {
			return;
		}
		String to_addr = email.getTo();
		List<Address> laddr = addressRepository.findByAddress(to_addr);
		Address addr;
		if( laddr.isEmpty() ) {
			addr = addressRepository.save( new Address(to_addr) );
		} else {
			addr = laddr.get(0);
		}
		email.setId(emailRepository.save( new Email(addr, email.getSubject(), email.getText(), email.getSentDate()) ).getId());
	}

	@Transactional
	@Override
	public void add(List<VerySimpleMail> emails) {
		if( emails==null || emails.isEmpty() ) {
			return;
		}
		emails.stream().forEach(this::add);
	}

	@Transactional
	@Override
	public void update(int id, LocalDateTime date) {
		if( emailRepository.setEmailSentDateById(id, date) == 0 ) {
			throw new IndexOutOfBoundsException("Index does not exist");
		}
	}

	@Transactional
	@Override
	public void delete(int id) {
		try {
			emailRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new IndexOutOfBoundsException("Index does not exist");
		}
	}

	@Transactional
	@Override
	public void clear() {
		emailRepository.deleteAll();
	}

}
