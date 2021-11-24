package com.stevens.tennis.repository;

import com.stevens.tennis.model.dao.Match;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MatchDAO extends CrudRepository<Match,Long> {

}
