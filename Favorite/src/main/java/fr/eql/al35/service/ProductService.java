package fr.eql.al35.service;

import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	public Set<Product> displayAllProducts() {
		return (Set<Product>) productRepository.findAll();
	}

	@Override
	public Set<Product> displayAvailableProducts() {
		return (Set<Product>) productRepository.listAvailableProducts();
	}

	@Override
	public Product displayProductById(int id) {
		return productRepository.findById(id).get();
	}

	@Override
	public Set<ProductType> displayAllCategories() {
		return (Set<ProductType>) productTypeRepository.findAll();
	}

	@Override
	public Set<Product> displayByProductType(ProductType productType) {
		return (Set<Product>) productRepository.findByProductType(productType);
	}

	@Override
	public Set<Design> displayAllDesign() {
		return (Set<Design>) designRepository.findAll();
	}

}
