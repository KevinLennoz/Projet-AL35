package fr.eql.al35.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import fr.eql.al35.entity.Command;
import fr.eql.al35.service.CommandIService;

@Controller
public class CommandController {
	
	@Autowired
	private CommandIService commandService;	
		
	@GetMapping("/myOrders")
	public String userCommands(Model model, HttpSession session) {
		
		List<Command> commands = new ArrayList<Command>(); 
		commands = commandService.findByUser(3);
		
		model.addAttribute("commands", commands);

	return "myOrders";
	}
	
	@GetMapping("/order/{id}")
	public String displayCommand(@PathVariable Integer id, Model model) {
	model.addAttribute("commande", commandService.displaybyId(id));
	return "/order";
	}
}
