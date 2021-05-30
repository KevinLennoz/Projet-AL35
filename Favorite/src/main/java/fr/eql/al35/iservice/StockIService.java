package fr.eql.al35.iservice;

import fr.eql.al35.entity.Stock;

public interface StockIService {

	public Stock displayStockById(int id);
	public Stock upDate(Integer id, Stock stock);
}
