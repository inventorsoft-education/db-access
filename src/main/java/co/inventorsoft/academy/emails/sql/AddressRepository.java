package co.inventorsoft.academy.emails.sql;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.inventorsoft.academy.emails.entity.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {
	
	List<Address> findByAddress(String address);

}
