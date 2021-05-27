package fr.eql.al35.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.eql.al35.entity.PayMode;
import fr.eql.al35.entity.Status;
import fr.eql.al35.entity.User;
import fr.eql.al35.entity.Vat;
import fr.eql.al35.iservice.AdminIService;
import fr.eql.al35.repository.PayModeIRepository;
import fr.eql.al35.repository.StatusIRepository;
import fr.eql.al35.repository.UserIRepository;
import fr.eql.al35.repository.VatIRepository;

@Service
@Transactional
public class AdminService implements AdminIService {
	
	@Autowired
	StatusIRepository statusRepo;
	
	@Autowired
	UserIRepository userRepo;
	
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
	public User updateUser(User user) {
		return userRepo.save(user);
	}

	@Override
	public User displayUser(Integer id) {
		return userRepo.findById(id).get();
	}
}
