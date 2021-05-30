package fr.eql.al35.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.eql.al35.entity.Product;
import fr.eql.al35.entity.Stock;
import fr.eql.al35.iservice.StockIService;
import fr.eql.al35.repository.StockIRepository;

@Service
public class StockService implements StockIService {

	@Autowired
	private StockIRepository stockRepository;
	
	@Override
	public Stock displayStockById(int id) {
		return stockRepository.findById(id).get();
	}
	
	@Override
	public Stock upDate(Integer id, Stock stock) {
		stock.setId(id);
        
		return stockRepository.save(stock);
	}
}
