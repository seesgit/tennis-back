package com.stevens.tennis.service.interfaces;

import com.stevens.tennis.model.dao.Player;

public interface RuleService {

    void playerWinGame(Player playerOneId, Player playerTwoId);

    void playerWinSet(Player playerOneId, Player playerTwoId);
}
