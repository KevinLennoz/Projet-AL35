package fr.eql.al35.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.eql.al35.entity.Product;
import fr.eql.al35.repository.ProductIRepository;



@Service
public class ProductService implements ProductIService {

	@Autowired
	private ProductIRepository productRepository;
	
	@Override
	public List<Product> displayAllProducts() {
		return (List<Product>) productRepository.findAll();
	}


	@Override
	public List<Product> displayAvailableProducts() {
		return (List<Product>) productRepository.FlofloAvailableFluflu();
	}
}
