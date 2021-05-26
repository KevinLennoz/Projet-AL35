package fr.eql.al35.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.eql.al35.entity.Article;
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
		article.setProduct(product.get());
		article.setPrice(product.get().getPrice());
	}
	
}
