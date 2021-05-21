package fr.eql.al35.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import fr.eql.al35.service.AccountIService;

@Controller
public class AccountController {
	
	@Autowired
	private AccountIService accountService;
	
	@GetMapping("/page1")
	public String testPage(Model model) {
		model.addAttribute("users", accountService.displayAllUsers());
		return "page1";
	}
}
