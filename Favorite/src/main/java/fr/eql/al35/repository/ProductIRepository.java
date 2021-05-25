package fr.eql.al35.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import fr.eql.al35.entity.Product;
import fr.eql.al35.entity.ProductType;


public interface ProductIRepository extends CrudRepository<Product, Integer>{
	
	Set<Product> findByProductType(ProductType productType);
	
	@Query("SELECT p FROM Product p WHERE p.refDeletionDate IS NULL")
	Set<Product> listAvailableProducts();
	
	

}
