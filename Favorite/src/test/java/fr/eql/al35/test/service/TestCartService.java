package fr.eql.al35.test.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.event.annotation.BeforeTestExecution;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import fr.eql.al35.entity.Article;
import fr.eql.al35.entity.Cart;
import fr.eql.al35.entity.CommandArticle;
import fr.eql.al35.service.CartIService;
import fr.eql.al35.service.ProductIService;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestCartService {
	
	@Autowired
	private CartIService cartService;
	
	@Autowired
	private ProductIService productService;
	
	@BeforeTestExecution
	public void initCart() {
		Cart cart = new Cart();
		Article article = new Article();
		Article article2 = new Article();
		Article article3 = new Article();
		article.setProduct(productService.displayProductById(2));
		article2.setProduct(productService.displayProductById(3));
		article3.setProduct(productService.displayProductById(4));
		List<CommandArticle> commandArticles = new ArrayList<>();
		CommandArticle item1 = new CommandArticle(1, 5, null, null, article); //mettre une taille quand import finit
		CommandArticle item2 = new CommandArticle(2, 4, null, null, article2);
		CommandArticle item3 = new CommandArticle(3, 6, null, null, article3);
		commandArticles.addAll(Arrays.asList(item1, item2, item3));
		cart.setCommandArticles(commandArticles);
	}

}
