package com.stevens.tennis.repository;

import com.stevens.tennis.model.dao.Set;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SetDAO extends CrudRepository<Set,Long> {

}
