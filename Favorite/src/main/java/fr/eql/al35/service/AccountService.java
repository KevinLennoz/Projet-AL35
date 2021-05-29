package fr.eql.al35.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.eql.al35.entity.Address;
import fr.eql.al35.entity.Gender;
import fr.eql.al35.entity.User;
import fr.eql.al35.entity.UserType;
import fr.eql.al35.iservice.AccountIService;
import fr.eql.al35.repository.AddressIRepository;
import fr.eql.al35.repository.GenderIRepository;
import fr.eql.al35.repository.UserIRepository;
import fr.eql.al35.repository.UserTypeIRepo;


@Service
@Transactional
public class AccountService implements AccountIService {

	@Autowired
	private UserIRepository userRepository;
	
	@Autowired
	private AddressIRepository addressRepository;
	
	@Autowired
	private GenderIRepository genderRepository;
	
	@Autowired
	UserTypeIRepo userTypeRepository;
	
	@Override
	public List<User> displayAllUsers() {
		return (List<User>) userRepository.findAll();
	}

	@Override
	public User getUser3() {
		Optional<User> user = userRepository.findById(3);
		return user.isPresent() ? user.get() : null;
	}

	@Override
	public List<Address> getAddressByUser(User user) {
		return addressRepository.findByUser(user);
	}

	@Override
	public User getAdminAccount() {
		Optional<User> user = userRepository.findById(2);
		return user.isPresent() ? user.get() : null;
	}
	
	@Override
	public List<Gender> getAllGenders(){
		return (List<Gender>) genderRepository.findAll();
	}

	@Override
	public List<UserType> getAllUserTypes() {
		return (List<UserType>) userTypeRepository.findAll();
	}
}
