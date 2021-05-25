package fr.eql.al35.service;

import fr.eql.al35.entity.Cart;
import fr.eql.al35.entity.CommandArticle;

public interface CartIService {
	
	int getCartProductsQuantity(Cart cart);
	double getTotalPriceCart(Cart cart);
	int addProduct(CommandArticle commandArticle);

}
