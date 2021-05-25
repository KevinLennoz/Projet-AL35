package fr.eql.al35.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.print.attribute.standard.DateTimeAtCompleted;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import fr.eql.al35.entity.Address;
import fr.eql.al35.entity.Cart;
import fr.eql.al35.entity.Command;
import fr.eql.al35.entity.PayMode;
import fr.eql.al35.entity.User;
import fr.eql.al35.service.AccountIService;
import fr.eql.al35.service.CommandIService;

import org.springframework.ui.Model;

@Controller
public class PaymentController {
	
	@Autowired
	CommandIService cmdService;
	
	@Autowired
	AccountIService accountService;

	@GetMapping("/payment")
	public String displayPayment(Model model, HttpSession session) {
		/*System.out.println("coucou getMapping CmdController");
		Cart sessionCart = (Cart) session.getAttribute("sessionCart");
		User sessionUser = (User) session.getAttribute("sessionUser");
		Command command = cmdService.convertCartToCommand(sessionCart);
		command.setUser(sessionUser);
		System.out.println("comment getMapping : " + command.toString());
		*/
		return "payment";
	}

	@PostMapping("/newCommand") 
	public String createNewCommand(Model model, HttpSession session) {
		Cart sessionCart = (Cart) session.getAttribute("sessionCart");
		User sessionUser = (User) session.getAttribute("sessionUser");
		Command command = cmdService.createCommand(sessionCart);
		System.out.println("command from Cart : " + command.toString()); 
		command.setUser(sessionUser);
		
		List<Address> addresses = accountService.getAddressByUser(sessionUser);
		command.setFacturationAddress(addresses.get(1));
		command.setDeliveryAddress(addresses.get(0));
		
		//a changer en fonction de son choix : 
		PayMode payMode = new PayMode();
		payMode.setId(1);
		command.setPayMode(payMode);
		command.setReference(writeReference(sessionUser, command));
		
		cmdService.saveCommand(command);
		System.out.println("fin methode post : " + command.toString());
		return "payment";
	}
	
	private String writeReference(User user, Command command) {
		StringBuilder reference = new StringBuilder();
		reference.append("CMD_");
		reference.append(LocalDateTime.now());
		reference.append("_Client_");
		reference.append(user.getId()); //a modif avec le n° Client en session
		return reference.toString();
	}

}
