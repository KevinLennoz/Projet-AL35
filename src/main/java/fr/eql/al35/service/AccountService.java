package fr.eql.al35.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.eql.al35.entity.User;
import fr.eql.al35.repository.UserRepository;


@Service
public class AccountService implements AccountIService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public List<User> displayAllUsers() {
		return (List<User>) userRepository.findAll();
	}
}
