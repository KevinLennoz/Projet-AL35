package fr.eql.al35.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import fr.eql.al35.entity.Address;
import fr.eql.al35.entity.Cart;
import fr.eql.al35.entity.Command;
import fr.eql.al35.entity.PayMode;
import fr.eql.al35.entity.User;
import fr.eql.al35.iservice.AccountIService;
import fr.eql.al35.iservice.CommandIService;

@Controller
public class PaymentController {
	
	@Autowired
	CommandIService cmdService;
	
	@Autowired
	AccountIService accountService;

	@GetMapping("/payment")
	public String displayPayment(Model model, HttpSession session) {
		return "payment";
	}

	@PostMapping("/newCommand") 
	public String createNewCommand(Model model, HttpSession session) {
		Cart sessionCart = (Cart) session.getAttribute("sessionCart");
		User sessionUser = (User) session.getAttribute("sessionUser");
		Command command = cmdService.createCommand(sessionCart);
		command.setUser(sessionUser); //a modifier si création de compte
		
		//
		List<Address> addresses = accountService.getAddressByUser(sessionUser);
		command.setFacturationAddress(addresses.get(1));
		command.setDeliveryAddress(addresses.get(0));
		//
		
		//a changer en fonction de son choix : 
		PayMode payMode = new PayMode();
		payMode.setId(1);
		command.setPayMode(payMode);
		command.setReference(writeReference(sessionUser, command));
		
		cmdService.saveCommand(command);
		return "payment";
	}
	
	private String writeReference(User user, Command command) {
		StringBuilder reference = new StringBuilder();
		reference.append("CMD_");
		reference.append(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd-hh-mm-ss")));
		reference.append("_Client_");
		reference.append(user.getId()); //a modif avec le n° Client en session
		return reference.toString();
	}

}
