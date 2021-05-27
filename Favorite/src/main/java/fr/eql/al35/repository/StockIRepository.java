package fr.eql.al35.repository;

import org.springframework.data.repository.CrudRepository;

import fr.eql.al35.entity.Product;
import fr.eql.al35.entity.Size;
import fr.eql.al35.entity.Stock;

public interface StockIRepository extends CrudRepository<Stock, Integer> {
	
	public Stock findByProductAndSize(Product product, Size size);
	
}
