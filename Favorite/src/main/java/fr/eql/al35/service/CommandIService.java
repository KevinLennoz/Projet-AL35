package fr.eql.al35.service;

import java.util.List;

import fr.eql.al35.entity.Cart;
import fr.eql.al35.entity.Command;

public interface CommandIService {
	
	public Command createCommand(Cart cart);
	public Command saveCommand(Command command);
	List<Command> displayAllCommands();
	Command updateCommand(Command command);
	
}
