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
	
}
