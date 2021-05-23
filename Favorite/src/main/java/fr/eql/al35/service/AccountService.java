package fr.eql.al35.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.eql.al35.entity.User;
import fr.eql.al35.repository.UserIRepository;


@Service
@Transactional
public class AccountService implements AccountIService {

	@Autowired
	private UserIRepository userRepository;
	
	@Override
	public List<User> displayAllUsers() {
		return (List<User>) userRepository.findAll();
	}
}
