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
import fr.eql.al35.entity.Custom;
import fr.eql.al35.entity.Design;
import fr.eql.al35.entity.Product;
import fr.eql.al35.entity.ProductType;
import fr.eql.al35.entity.Size;
import fr.eql.al35.repository.DesignIRepository;
import fr.eql.al35.repository.ProductIRepository;
import fr.eql.al35.repository.ProductTypeIRepository;
import fr.eql.al35.repository.ProductTypeLocationIRepository;
import fr.eql.al35.repository.SizeIRepository;

@Service
@Transactional
public class ProductService implements ProductIService {

	@Autowired
	private ProductIRepository productRepository;
	
	@Autowired
	private ProductTypeIRepository productTypeRepository;
	
	@Autowired
	private ProductTypeLocationIRepository productTypeLocationIRepository;
	
	@Autowired
	private DesignIRepository designRepository;
		
	@Autowired
	private SizeIRepository sizeRepo;
	
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
		Article article1 = new Article(5, 40.99, displayProductById(2), null, sizeRepo.findById("34").get(), null);
		Article article2 = new Article(4, 60.24, displayProductById(3), null, sizeRepo.findById("XL").get(), null);
		Article article3 = new Article(6, 45.24, displayProductById(6), null, sizeRepo.findById("38").get(), null);
		
		//important:
		Custom custom = new Custom(designRepository.findById(1).get().getPrice(), productTypeLocationIRepository.findById(6).get(), designRepository.findById(1).get());
		Custom custom2 = new Custom(designRepository.findById(2).get().getPrice(), productTypeLocationIRepository.findById(7).get(), designRepository.findById(2).get());
		custom.setArticle(article3);
		custom2.setArticle(article3);
		
		Set<Custom> customs = new HashSet<Custom>();
		customs.add(custom);
		customs.add(custom2);
		article3.setCustoms(customs);
		articles.addAll(Arrays.asList(article1, article2, article3));
		cart.setArticles(articles);
		cart.setPrice(article1.getPrice() + article2.getPrice() + article3.getPrice());
		return cart;
	}

	@Override
	public Product upDate(Product product) {
			return productRepository.save(product);
	}
	

}
