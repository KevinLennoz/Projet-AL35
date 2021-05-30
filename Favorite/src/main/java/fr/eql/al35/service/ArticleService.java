package fr.eql.al35.service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.eql.al35.entity.Article;
import fr.eql.al35.entity.Custom;
import fr.eql.al35.entity.Product;
import fr.eql.al35.iservice.ArticleIService;
import fr.eql.al35.repository.ProductIRepository;

@Service
@Transactional
public class ArticleService implements ArticleIService{
	
	@Autowired
	private ProductIRepository productRepo;
	

	@Override
	public void addProduit(Integer id, Article article) {
		Optional<Product> product = productRepo.findById(id);
		
		if(product.isPresent()) {
			article.setProduct(product.get());
			article.setPrice(product.get().getPrice());
		}
	}
	
	@Override
	public void addCustoms(List<Custom> customs, Article article) {
		Set<Custom> articleCustoms = new HashSet<Custom>();
		for (Custom custom : customs) {
			articleCustoms.add(custom);
			article.setPrice(article.getPrice()+custom.getPrice());
		}
		article.setCustoms(articleCustoms);
	}
	
}
