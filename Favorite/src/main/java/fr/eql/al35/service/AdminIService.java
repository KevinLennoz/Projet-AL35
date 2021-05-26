package fr.eql.al35.service;

import java.util.List;

import fr.eql.al35.entity.PayMode;
import fr.eql.al35.entity.Status;
import fr.eql.al35.entity.User;
import fr.eql.al35.entity.Vat;

public interface AdminIService {
	
	List<Status> displayAllStatus();
	List<User> displayAllUsers();
	List<Vat> displayAllVats();
	List<PayMode> displayAllPayModes();
	User updateUser(User user);

}
