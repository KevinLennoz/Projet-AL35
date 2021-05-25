package fr.eql.al35.service;

import fr.eql.al35.entity.Cart;
import fr.eql.al35.entity.Command;

public interface CommandIService {
	
	public Command convertCartToCommand(Cart cart);
	public Command createCommand(Command command);

}
