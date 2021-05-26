package fr.eql.al35.service;

import java.time.LocalDateTime;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.eql.al35.entity.Article;
import fr.eql.al35.entity.Cart;
import fr.eql.al35.entity.Command;
import fr.eql.al35.entity.Custom;
import fr.eql.al35.entity.Stock;
import fr.eql.al35.entity.Vat;

import fr.eql.al35.repository.AddressIRepository;
import fr.eql.al35.repository.CityIRepository;

import fr.eql.al35.repository.ArticleIRepository;

import fr.eql.al35.repository.CommandIRepository;

import fr.eql.al35.repository.PayModeIRepository;

import fr.eql.al35.repository.CustomIRepository;
import fr.eql.al35.repository.SizeIRepository;

import fr.eql.al35.repository.StatusIRepository;
import fr.eql.al35.repository.StockIRepository;
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
	
	@Autowired
	AddressIRepository addressRepo;
	
	@Autowired
	CityIRepository cityRepo;
	
	@Autowired
	PayModeIRepository payModeRepo;
	
	// plus besoin de ces repos quand le front sera finit : 

	@Autowired
	ArticleIRepository articleRepo;

	@Autowired
	CustomIRepository customRepo;

	@Autowired
	UserIRepository userRepo;
	
	@Autowired
	StockIRepository stockRepo;

	@Override
	public Command createCommand(Cart cart) {
		Command command = new Command();
		command.setArticles(cart.getArticles());
		command.setTaxOutPrice(cart.getPrice());
		return command;
	}

	@Override
	public Command saveCommand(Command command) {
		Vat vat = vatRepo.findById(5).get(); //en dur global pour la command, a modifier pour chaque article plus tard
		command.setVat(vat);
		command.setTaxInPrice(command.getTaxOutPrice() + command.getTaxOutPrice()*vat.getRate());
		command.setCreationDate(LocalDateTime.now());
		command.setStatus(statusRepo.findById(1).get());
		articleRepo.saveAll(command.getArticles());
		cmdRepo.save(command);
		for (Article article : command.getArticles()) {
			article.setCommand(command);
			updateStock(article);
		}
		articleRepo.saveAll(command.getArticles());
		return command;
	}

	@Override
	public List<Command> findByUser(Integer user) {
		return cmdRepo.findByUser(user);
	}

	@Override
	public Command displaybyId(Integer id) {
		return cmdRepo.findById(id).get();
	}

	@Override
	public List<Command> displayAllCommands() {
		return (List<Command>) cmdRepo.findAll();
	}

	@Override
	public Command updateCommand(Command command) {
		addressRepo.save(command.getDeliveryAddress());
		addressRepo.save(command.getFacturationAddress());
		cityRepo.save(command.getDeliveryAddress().getCity());
		cityRepo.save(command.getFacturationAddress().getCity());
		payModeRepo.save(command.getPayMode());
		vatRepo.save(command.getVat());
		statusRepo.save(command.getStatus());
		return cmdRepo.save(command);
	}

	private void updateStock(Article article) {
		Stock stock = stockRepo.findStockByProductAndSize(article.getProduct(), article.getSize());
		stock.setQuantity(stock.getQuantity() - article.getQuantity());
		stockRepo.save(stock);
	}

}
