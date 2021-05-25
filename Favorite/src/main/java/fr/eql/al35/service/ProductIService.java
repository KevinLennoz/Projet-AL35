package fr.eql.al35.service;

import java.util.List;
import java.util.Set;

import fr.eql.al35.entity.Design;
import fr.eql.al35.entity.Product;
import fr.eql.al35.entity.ProductType;

public interface ProductIService {
	
	Set<Product> displayAllProducts();
	Set<Product> displayAvailableProducts();
	Product displayProductById(int id);
	Set<ProductType> displayAllCategories();
	Set<Product> displayByProductType(ProductType productType);
	Set<Design> displayAllDesign();

}
