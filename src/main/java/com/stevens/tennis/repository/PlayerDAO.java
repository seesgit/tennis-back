package com.stevens.tennis.repository;

import com.stevens.tennis.model.dao.Player;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerDAO extends CrudRepository<Player,Long> {

}
