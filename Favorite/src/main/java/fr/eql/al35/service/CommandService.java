package fr.eql.al35.service;

import java.time.LocalDateTime;

import fr.eql.al35.entity.Cart;
import fr.eql.al35.entity.Command;

public class CommandService implements CommandIService {

	@Override
	public Command createCommand(Cart cart) {
		Command command = new Command();
		command.setCreationDate(LocalDateTime.now());
		command.setReference("ESSAI REFERENCE");
		
		// en cours de dev 
		return command;
	}

	@Override
	public Command validateCommand(Command command) {

		// en cours de dev
		return null;
	}

}
