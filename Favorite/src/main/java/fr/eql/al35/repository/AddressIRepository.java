package fr.eql.al35.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import fr.eql.al35.entity.Address;
import fr.eql.al35.entity.City;
import fr.eql.al35.entity.User;

public interface AddressIRepository extends CrudRepository<Address, Integer> {
	List<Address> findByUser(User user);
	Integer findIdByNameAndStreetAndCity(String name, String street, City city);

}
