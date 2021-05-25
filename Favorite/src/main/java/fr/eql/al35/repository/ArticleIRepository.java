package fr.eql.al35.repository;

import org.springframework.data.repository.CrudRepository;

import fr.eql.al35.entity.Article;

public interface ArticleIRepository extends CrudRepository<Article, Integer> {

}
