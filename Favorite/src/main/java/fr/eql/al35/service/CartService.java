package fr.eql.al35.service;


import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import fr.eql.al35.entity.Cart;
import fr.eql.al35.entity.CommandArticle;
import fr.eql.al35.entity.Product;
import fr.eql.al35.entity.Stock;

@Service
@Transactional
public class CartService implements CartIService {

	@Override
	public int getCartProductsQuantity(Cart cart) {

		Set<CommandArticle> commandArticles = cart.getCommandArticles();
		int articlesQuantity = 0;

		for (CommandArticle commandArticle : commandArticles) {
			articlesQuantity += commandArticle.getQuantity();
		}

		return articlesQuantity;
	}

	@Override
	public double getTotalPriceCart(Cart cart) {
		System.out.println("je suis dans getTotalPrice");
		Set<CommandArticle> commandArticles = cart.getCommandArticles();
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
	public void addArticle(Cart cart, CommandArticle commandArticle) {
		cart.getCommandArticles().add(commandArticle);
		cart.setArticlesQuantity(cart.getArticlesQuantity()+commandArticle.getQuantity());
	}
	
	@Override
	public boolean enoughInStock(CommandArticle commandArticle, Product product) {
		boolean inStock = false;
		System.out.println(commandArticle);
		System.out.println(product);
		for (Stock stock : product.getStocks()) {
			if (stock.getSize().getLabel()==commandArticle.getSize().getLabel()) {
				if (stock.getQuantity()>=commandArticle.getQuantity()) {
					inStock=true;
				}
			}
		}
		return inStock;
	}



	

	

}
