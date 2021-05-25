package fr.eql.al35.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.eql.al35.entity.Address;
import fr.eql.al35.entity.User;
import fr.eql.al35.repository.AddressIRepository;
import fr.eql.al35.repository.UserIRepository;


@Service
@Transactional
public class AccountService implements AccountIService {

	@Autowired
	private UserIRepository userRepository;
	
	@Autowired
	private AddressIRepository addressRepository;
	
	@Override
	public List<User> displayAllUsers() {
		return (List<User>) userRepository.findAll();
	}

	@Override
	public User getUser3() {
		return userRepository.findById(3).get();
	}

	@Override
	public List<Address> getAddressByUser(User user) {
		return addressRepository.findByUser(user);
	}
}
