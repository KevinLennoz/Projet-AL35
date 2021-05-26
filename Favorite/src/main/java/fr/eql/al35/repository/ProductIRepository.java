package fr.eql.al35.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import fr.eql.al35.entity.Product;
import fr.eql.al35.entity.ProductType;


public interface ProductIRepository extends CrudRepository<Product, Integer>{
	
	List<Product> findByProductType(ProductType productType);
	
	@Query("SELECT p FROM Product p WHERE p.refDeletionDate IS NULL")
	List<Product> listAvailableProducts();
	
	@Query("update Product p set p.name = :name, p.quantity = :quantity, p.price = :price where p.id = :id")
	@Modifying
	Product updateProduct(@Param("name")String name, @Param("quantity")Integer quantity, @Param("price")Double price, @Param("id") int id);
	

}
