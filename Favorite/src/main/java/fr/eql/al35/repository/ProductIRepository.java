package fr.eql.al35.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import fr.eql.al35.entity.Product;
import fr.eql.al35.entity.ProductType;


public interface ProductIRepository extends CrudRepository<Product, Integer>{
	
	List<Product> findByProductType(ProductType productType);
	
	@Query("SELECT p FROM Product p WHERE p.refDeletionDate IS NULL")
	List<Product> listAvailableProducts();


}
