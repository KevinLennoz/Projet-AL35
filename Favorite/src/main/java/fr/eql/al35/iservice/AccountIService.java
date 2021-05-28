package fr.eql.al35.iservice;

import java.util.List;

import fr.eql.al35.entity.Address;
import fr.eql.al35.entity.Gender;
import fr.eql.al35.entity.User;
import fr.eql.al35.entity.UserType;

public interface AccountIService {
	
	List<User> displayAllUsers();
	User getUser3();//pour avoir un utilisateur en dur en session
	List<Address> getAddressByUser(User user);
	User getAdminAccount();
	List<Gender> getAllGenders();
	List<UserType> getAllUserTypes();

}

