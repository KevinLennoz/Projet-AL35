package fr.eql.al35.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import fr.eql.al35.entity.Product;
import fr.eql.al35.entity.Size;
import fr.eql.al35.entity.Stock;

public interface StockIRepository extends CrudRepository<Stock, Integer> {
	
	@Query("SELECT s FROM Stock s WHERE s.product = ?1 AND s.size = ?2")
	public Stock findStockByProductAndSize(Product product, Size size);
	
}
