package fr.eql.al35.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import fr.eql.al35.entity.Cart;
import fr.eql.al35.entity.CommandArticle;

@Service
@Transactional
public class CartService implements CartIService {

	@Override
	public int getCartProductsQuantity(Cart cart) {

		List<CommandArticle> commandArticles = cart.getCommandArticles();
		int articlesQuantity = 0;

		for (CommandArticle commandArticle : commandArticles) {
			articlesQuantity += commandArticle.getQuantity();
		}

		return articlesQuantity;
	}

	@Override
	public double getTotalPriceCart(Cart cart) {
		System.out.println("je suis dans getTotalPrice");
		List<CommandArticle> commandArticles = cart.getCommandArticles();
		double total = 0.0;
		double sousTotal = 0.0;
		for (CommandArticle commandArticle : commandArticles) {
			sousTotal = commandArticle.getArticle().getProduct().getPrice() * commandArticle.getQuantity();
			System.out.println(sousTotal);
			total = total + sousTotal;
			System.out.println(total);
	
	
			System.out.println(commandArticle.getQuantity());
		}
		System.out.println(total);
		
		return total;
	}

	@Override
	public int addProduct(CommandArticle commandArticle) {
		return commandArticle.getQuantity() + 1;
	}



	

	

}
