package fr.eql.al35.repository;

import org.springframework.data.repository.CrudRepository;

import fr.eql.al35.entity.Product;

public interface CommandIRepository extends CrudRepository<Product, Integer> {

}
