package fr.eql.al35.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import fr.eql.al35.entity.Command;
import fr.eql.al35.entity.Product;
import fr.eql.al35.entity.User;
import fr.eql.al35.iservice.AdminIService;
import fr.eql.al35.iservice.CommandIService;
import fr.eql.al35.iservice.ProductIService;

@Controller
public class AdminController {

	@Autowired
	ProductIService productService;
	
	@Autowired
	CommandIService commandService;
	
	@Autowired
	AdminIService adminService;

	@GetMapping("/admin/product")
	public String displayAdminProduct( Model model) {
		model.addAttribute("products", productService.displayAllProducts());
		return "adminProduct";
	}
	
	@GetMapping("/admin/users")
	public String displayUsers(Model model) {
		model.addAttribute("users", adminService.displayAllUsers());
		return "adminUsers";
	}
	
	@GetMapping("/admin/home")
	public String redirectAdminHome( Model model) {
		return "adminHome";
	}
	
	@PostMapping("/upDateProducts")
	public String upDateProducts(@ModelAttribute("product")Product product, Model model) {
		productService.upDate(product);
		model.addAttribute("products", productService.displayAllProducts());

		return "adminProduct";
	}
	
	@GetMapping("/admin/command")
	public String displayAdminCommand( Model model) {
		model.addAttribute("commands", commandService.displayAllCommands());
		model.addAttribute("statusRef", adminService.displayAllStatus());
		model.addAttribute("vatRef", adminService.displayAllVats());
		model.addAttribute("payModeRef", adminService.displayAllPayModes());
		
		return "adminCommand";
	}
	
	@PostMapping("/upDateCommands")
	public String upDateCommands(@ModelAttribute("command")Command command, Model model) {
		commandService.updateCommand(command);
		model.addAttribute("command", commandService.updateCommand(command));
		model.addAttribute("commands", commandService.displayAllCommands());
		model.addAttribute("statusRef", adminService.displayAllStatus());
		model.addAttribute("vatRef", adminService.displayAllVats());
		model.addAttribute("payModeRef", adminService.displayAllPayModes());
		return "adminCommand";
	}

	@PostMapping("/updateUser")
	public String upDateProducts(@ModelAttribute("user")User user, Model model) {	
		adminService.updateUser(user);
		return "adminUsers";
	}
}
