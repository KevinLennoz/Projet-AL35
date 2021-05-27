package fr.eql.al35.service;

import java.time.LocalDateTime;
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
import fr.eql.al35.iservice.ProductIService;
import fr.eql.al35.repository.ArticleIRepository;
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
	
	@Autowired
	private ArticleIRepository articleRepository;
	
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
		Article article1 = articleRepository.findById(1).get();
		Article article2 = articleRepository.findById(5).get();
		Article article3 = articleRepository.findById(3).get();
		
//		//important:
//		Custom custom = new Custom(designRepository.findById(1).get().getPrice(), productTypeLocationIRepository.findById(6).get(), designRepository.findById(1).get());
//		Custom custom2 = new Custom(designRepository.findById(2).get().getPrice(), productTypeLocationIRepository.findById(7).get(), designRepository.findById(2).get());
//		custom.setArticle(article3);
//		custom2.setArticle(article3);
		
//		Set<Custom> customs = new HashSet<Custom>();
//		customs.add(custom);
//		customs.add(custom2);
//		article3.setCustoms(customs);
		
		articles.addAll(Arrays.asList(article1, article2, article3));
		cart.setArticles(articles);
		cart.setPrice(article1.getPrice()*article1.getQuantity() + article2.getPrice()*article2.getQuantity() + article3.getPrice()*article3.getQuantity());
		return cart;
	}

	@Override
	public Product upDate(Integer id, Product product) {
		product.setId(id);
		return productRepository.save(product);
	}

	@Override
	public void setDeleteProduct(Integer id) {
		Product product = productRepository.findById(id).get();
		product.setRefDeletionDate(LocalDateTime.now());
	}
	

}
