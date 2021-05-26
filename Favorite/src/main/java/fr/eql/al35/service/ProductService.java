package fr.eql.al35.service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.eql.al35.entity.Article;
import fr.eql.al35.entity.Cart;
import fr.eql.al35.entity.Design;
import fr.eql.al35.entity.Product;
import fr.eql.al35.entity.ProductType;
import fr.eql.al35.repository.DesignIRepository;
import fr.eql.al35.repository.ProductIRepository;
import fr.eql.al35.repository.ProductTypeIRepository;

@Service
@Transactional
public class ProductService implements ProductIService {

	@Autowired
	private ProductIRepository productRepository;
	
	@Autowired
	private ProductTypeIRepository productTypeRepository;
	
	@Autowired
	private DesignIRepository designRepository;
	
	@Override
	public List<Product> displayAllProducts() {
		return (List<Product>) productRepository.findAll();
	}

	@Override
	public List<Product> displayAvailableProducts() {
		return (List<Product>)productRepository.listAvailableProducts();
	}

	@Override
	public Product displayProductById(int id) {
		return productRepository.findById(id).get();
	}

	@Override
	public List<ProductType> displayAllCategories() {
		return (List<ProductType>)productTypeRepository.findAll();
	}

	@Override
	public List<Product> displayByProductType(ProductType productType) {
		return (List<Product>) productRepository.findByProductType(productType);
	}

	@Override
	public List<Design> displayAllDesign() {
		return (List<Design>) designRepository.findAll();
	}
	
	@Override
	public Cart generateCartDatas() {			//TODO A retirer une fois le programme fonctionnel

		Cart cart = new Cart();
		Set<Article> articles = new HashSet<>();
		Article article1 = new Article(1, 5, 40.99, displayProductById(2), null, null, null); //mettre une taille quand import finit
		Article article2 = new Article(2, 4, 60.24, displayProductById(3), null, null, null);
		Article article3 = new Article(3, 6, 45.24, displayProductById(4), null, null, null);
		articles.addAll(Arrays.asList(article1, article2, article3));
		cart.setArticles(articles);
		
		return cart;
	}

	@Override
	public Product upDate(Product product) {
			return productRepository.save(product);
	}
	

}
