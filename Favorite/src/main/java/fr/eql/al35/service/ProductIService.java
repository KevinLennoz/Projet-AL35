package fr.eql.al35.service;

import java.util.List;

import fr.eql.al35.entity.Product;

public interface ProductIService {
	
	List<Product> displayAllProducts();
	List<Product> displayAvailableProducts();

}
