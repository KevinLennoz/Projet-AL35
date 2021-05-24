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
import fr.eql.al35.entity.Product;
import fr.eql.al35.service.CartIService;
import fr.eql.al35.service.ProductIService;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestDisplayVestes {
		
	@Autowired
	private ProductIService productService;
	
	@BeforeTestExecution
	public void initVeste() {

	
		List<Product> commandArticles = new ArrayList<>();
		commandArticles.addAll(productService.displayProductVeste());
		for (Product product : commandArticles) {
			System.out.println(product.toString());
		}

	
	}

}
