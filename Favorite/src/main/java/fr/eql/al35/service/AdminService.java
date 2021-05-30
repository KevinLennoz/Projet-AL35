package fr.eql.al35.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.eql.al35.entity.Gender;
import fr.eql.al35.entity.PayMode;
import fr.eql.al35.entity.Status;
import fr.eql.al35.entity.User;
import fr.eql.al35.entity.UserType;
import fr.eql.al35.entity.Vat;
import fr.eql.al35.iservice.AdminIService;
import fr.eql.al35.repository.GenderIRepository;
import fr.eql.al35.repository.PayModeIRepository;
import fr.eql.al35.repository.StatusIRepository;
import fr.eql.al35.repository.UserIRepository;
import fr.eql.al35.repository.UserTypeIRepo;
import fr.eql.al35.repository.VatIRepository;

@Service
@Transactional
public class AdminService implements AdminIService {
	
	@Autowired
	StatusIRepository statusRepo;
	
	@Autowired
	UserIRepository userRepo;
	
	@Autowired
	GenderIRepository genderRepo;
	
	@Autowired
	UserTypeIRepo userTypeRepo;
	
	@Autowired
	VatIRepository vatRepo;
	
	@Autowired
	PayModeIRepository payModeRepo;

	@Override
	public List<Status> displayAllStatus() {
		return (List<Status>) statusRepo.findAll();
	}

	@Override
	public List<User> displayAllUsers() {
		return (List<User>) userRepo.findAll();
	}

	@Override
	public List<Vat> displayAllVats() {
		return (List<Vat>) vatRepo.findAll();
	}

	@Override
	public List<PayMode> displayAllPayModes() {
		return (List<PayMode>) payModeRepo.findAll();
	}

	@Override
	public User updateUser(User user, Integer id) {

		Optional<User> optionnalUser = userRepo.findById(id);
		User existingUser = new User();
		
		if(optionnalUser.isPresent()) {
			existingUser = optionnalUser.get();
			existingUser.setId(user.getId());
			existingUser.setName(user.getName());
			existingUser.setSurname(user.getSurname());
			existingUser.setEmail(user.getEmail());
			existingUser.setLogin(user.getLogin());
			existingUser.setPassword(user.getPassword());
			existingUser.setPhoneNumber(user.getPhoneNumber());
			existingUser.setSubscribingDate(user.getSubscribingDate());
			existingUser.setUnsubscribingDate(user.getUnsubscribingDate());
			existingUser.setBirthDate(user.getBirthDate());
			
			Optional<Gender> gender = genderRepo.findById(user.getGender().getId());
			existingUser.setGender(gender.isPresent() ? gender.get() : null);
			
			Optional<UserType> userType = userTypeRepo.findById(user.getUserType().getId());
			existingUser.setUserType(userType.isPresent() ? userType.get() : null);
		}

		return existingUser;
	}

	@Override
	public User displayUser(Integer id) {
		Optional<User> user = userRepo.findById(id);
		return user.isPresent() ? user.get() : null;
	}
}
