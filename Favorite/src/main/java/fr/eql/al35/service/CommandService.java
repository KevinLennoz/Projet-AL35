package fr.eql.al35.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;

import fr.eql.al35.entity.Cart;
import fr.eql.al35.entity.Command;
import fr.eql.al35.entity.Vat;
import fr.eql.al35.repository.CommandIRepository;
import fr.eql.al35.repository.StatusIRepository;
import fr.eql.al35.repository.UserIRepository;
import fr.eql.al35.repository.VatIRepository;

public class CommandService implements CommandIService {
	
	@Autowired
	CommandIRepository cmdRepo;
	
	@Autowired
	StatusIRepository statusRepo;
	
	@Autowired
	VatIRepository vatRepo;
	
	// plus besoin de ces repos quand le front sera finit : 
	@Autowired
	UserIRepository userRepo;

	@Override
	public Command convertCartToCommand(Cart cart) {
		Command command = new Command();
		command.setCommandArticles(cart.getCommandArticles());
		command.setTaxOutPrice(cart.getPrice());
		return command;
	}
	
	@Override
	public Command createCommand(Command command) {
		command.setTaxInPrice(command.getTaxOutPrice() + command.getTaxOutPrice()*getVat().getRate()); //implémenter la VAT later
		command.setCreationDate(LocalDateTime.now());
		cmdRepo.save(command);
		return command;
	}
	
	
	
	private Vat getVat() {
		return (Vat) vatRepo.findById(5).get(); //taux de 0, en dur
	}


}
