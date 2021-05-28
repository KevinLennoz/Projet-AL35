package fr.eql.al35.iservice;

import java.util.List;

import fr.eql.al35.entity.Article;
import fr.eql.al35.entity.Custom;

public interface ArticleIService {
	
	public void addProduit(Integer id, Article article);
	public void addCustoms(List<Custom> customs, Article article);

}
