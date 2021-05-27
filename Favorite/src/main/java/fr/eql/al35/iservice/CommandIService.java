package fr.eql.al35.iservice;

import java.util.List;

import fr.eql.al35.entity.Cart;
import fr.eql.al35.entity.Command;
import fr.eql.al35.entity.User;

public interface CommandIService {
	
	public Command createCommand(Cart cart, Command command);
	public Command saveCommand(Command command);
	public List<Command> findByUser(Integer user);
	public Command displaybyId(Integer id);
	List<Command> displayAllCommands();
	Command updateCommand(Command command);
	public void saveUser(User user);
	
}
