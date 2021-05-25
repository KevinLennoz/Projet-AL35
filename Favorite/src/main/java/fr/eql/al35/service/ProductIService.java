package fr.eql.al35.service;

import java.util.List;
import java.util.Set;

import fr.eql.al35.entity.Design;
import fr.eql.al35.entity.Product;
import fr.eql.al35.entity.ProductType;

public interface ProductIService {
	
	List<Product> displayAllProducts();
	List<Product> displayAvailableProducts();
	Product displayProductById(int id);
	List<ProductType> displayAllCategories();
	List<Product> displayByProductType(ProductType productType);
	Set<Design> displayAllDesign();

}
