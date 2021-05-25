package fr.eql.al35.service;


import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import fr.eql.al35.entity.Cart;
import fr.eql.al35.entity.Article;
import fr.eql.al35.entity.Product;
import fr.eql.al35.entity.Stock;

@Service
@Transactional
public class CartService implements CartIService {

	@Override
	public int getCartProductsQuantity(Cart cart) {

		Set<Article> articles = cart.getArticles();
		int articlesQuantity = 0;

		for (Article article : articles) {
			articlesQuantity += article.getQuantity();
		}

		return articlesQuantity;
	}

	@Override
	public double getTotalPriceCart(Cart cart) {
		System.out.println("je suis dans getTotalPrice");
		Set<Article> articles = cart.getArticles();
		double total = 0.0;
		double sousTotal = 0.0;
		for (Article article : articles) {
			sousTotal = article.getProduct().getPrice() * article.getQuantity();
			System.out.println(sousTotal);
			total = total + sousTotal;
			System.out.println(total);
	
	
			System.out.println(article.getQuantity());
		}
		System.out.println(total);
		
		return total;
	}


	@Override
	public void addArticle(Cart cart, Article article) {
		cart.getArticles().add(article);
		cart.setArticlesQuantity(cart.getArticlesQuantity()+article.getQuantity());
	}
	
	@Override
	public boolean enoughInStock(Article article, Product product) {
		boolean inStock = false;
		System.out.println(article);
		System.out.println(product);
		for (Stock stock : product.getStocks()) {
			if (stock.getSize().getLabel()==article.getSize().getLabel()) {
				if (stock.getQuantity()>=article.getQuantity()) {
					inStock=true;
				}
			}
		}
		return inStock;
	}



	

	

}
