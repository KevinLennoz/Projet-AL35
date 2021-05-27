package fr.eql.al35.iservice;

import fr.eql.al35.entity.Cart;
import fr.eql.al35.entity.Article;
import fr.eql.al35.entity.Product;


public interface CartIService {
	
	int getCartProductsQuantity(Cart cart);
	double getTotalPriceCart(Cart cart);
	public boolean enoughInStock(Article article, Product product);
	void addArticle(Cart cart, Article article);
	public Article getArticle(Cart cart, int index);
	public void removeArticle(Cart cart, int index);

}
