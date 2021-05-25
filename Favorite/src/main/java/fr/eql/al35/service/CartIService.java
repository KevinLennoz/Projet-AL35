package fr.eql.al35.service;

import fr.eql.al35.entity.Cart;
import fr.eql.al35.entity.Article;
import fr.eql.al35.entity.Product;


public interface CartIService {
	
	int getCartProductsQuantity(Cart cart);
	double getTotalPriceCart(Cart cart);
	public boolean enoughInStock(Article article, Product product);
	void addArticle(Cart cart, Article article);

}
