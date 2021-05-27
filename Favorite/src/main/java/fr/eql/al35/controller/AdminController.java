package fr.eql.al35.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
		return "adminProducts";
	}
	
	@GetMapping("/admin/users")
	public String displayUsers(Model model) {
		model.addAttribute("users", adminService.displayAllUsers());
		return "adminUsers";
	}
	
	@GetMapping("/admin/users/{id}")
	public String displayUser(@PathVariable Integer id, Model model) {
		System.out.println(adminService.displayUser(id));
		model.addAttribute("user", adminService.displayUser(id));
		return "adminUserInfo";
	}
	
	@GetMapping("/admin/commands/{id}")
	public String displayCommand(@PathVariable Integer id, Model model) {
		model.addAttribute("command", commandService.displaybyId(id));
		return "adminCommandInfo";
	}
	
	@GetMapping("/admin/home")
	public String redirectAdminHome( Model model) {
		return "adminHome";
	}
	
	@PostMapping("/upDateProducts")
	public String upDateProducts(@ModelAttribute("product")Product product, @RequestParam("idProduct") Integer idProduct, Model model) {
		System.out.println(product.toString());
		model.addAttribute("productTypes", productService.displayAllCategories());
		model.addAttribute("product", productService.upDate(idProduct, product));

		return "adminProductInfo";
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
	public String updateUser(@RequestParam("idUser")Integer id, @ModelAttribute("command")User command, Model model) {	
		System.out.println(user);
		System.out.println(adminService.updateUser(user));
		return "adminUsers";
	}
	
	@GetMapping("/admin/products/{id}")
	public String displayProduct(@PathVariable Integer id, Model model) {
		model.addAttribute("product", productService.displayProductById(id));
		model.addAttribute("productTypes", productService.displayAllCategories());
		return "adminProductInfo";
	}
	@GetMapping("/admin/products/delete/{id}")
	public String deleteProduct(@PathVariable Integer id, Model model) {
		model.addAttribute("products", productService.displayAllProducts());
		productService.setDeleteProduct(id);
		return "adminProducts";
	}
}
