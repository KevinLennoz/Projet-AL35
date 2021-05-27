package fr.eql.al35.repository;

import org.springframework.data.repository.CrudRepository;

import fr.eql.al35.entity.City;

public interface CityIRepository extends CrudRepository<City, Integer> {
}
