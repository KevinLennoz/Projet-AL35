package fr.eql.al35.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import fr.eql.al35.entity.Command;

public interface CommandIRepository extends CrudRepository<Command, Integer> {
	
	@Query("SELECT c FROM Command c WHERE user_id = ?1")
	public List<Command> findByUser(Integer id);
}
