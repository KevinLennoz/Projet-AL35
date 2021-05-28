package fr.eql.al35.repository;

import org.springframework.data.repository.CrudRepository;

import fr.eql.al35.entity.Gender;

public interface GenderIRepository extends CrudRepository<Gender, Integer> {

}
