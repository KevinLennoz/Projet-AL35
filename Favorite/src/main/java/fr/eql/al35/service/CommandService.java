package fr.eql.al35.service;

import java.time.LocalDateTime;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.eql.al35.entity.Cart;
import fr.eql.al35.entity.Command;
import fr.eql.al35.entity.Vat;
import fr.eql.al35.repository.CommandIRepository;
import fr.eql.al35.repository.StatusIRepository;
import fr.eql.al35.repository.UserIRepository;
import fr.eql.al35.repository.VatIRepository;

@Service
@Transactional
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
	public Command createCommand(Cart cart) {
		System.out.println("coucou convertCartToCommand");
		Command command = new Command();
		command.setCommandArticles(cart.getCommandArticles());
		command.setTaxOutPrice(cart.getPrice());
		return command;
	}
	
	@Override
	public Command saveCommand(Command command) {
		System.out.println("coucou createCommand");
		
		Vat vat = vatRepo.findById(5).get(); //en dur global pour la command, a modifier pour chaque article plus tard
		command.setVat(vat);
		command.setTaxInPrice(command.getTaxOutPrice() + command.getTaxOutPrice()*vat.getRate());
		command.setCreationDate(LocalDateTime.now());
		command.setStatus(statusRepo.findById(1).get());
		cmdRepo.save(command);
		return command;
	}
}
