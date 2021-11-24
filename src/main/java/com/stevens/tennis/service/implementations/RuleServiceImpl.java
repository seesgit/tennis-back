package com.stevens.tennis.service.implementations;

import com.stevens.tennis.model.dao.Player;
import com.stevens.tennis.model.dao.Set;
import com.stevens.tennis.model.enumeration.ScoreEnum;
import com.stevens.tennis.service.interfaces.RuleService;
import org.springframework.stereotype.Service;

@Service
public class RuleServiceImpl implements RuleService {

    public RuleServiceImpl(){}

    @Override
    public void playerWinGame(Player player, Player playerTwo) {

        // Win the game
        if(player.getMatch().getScore() == ScoreEnum.AVANTAGE &&
                (playerTwo.getMatch().getScore() == ScoreEnum.QUINZE ||
            playerTwo.getMatch().getScore() == ScoreEnum.TRENTE ||
            playerTwo.getMatch().getScore() == ScoreEnum.ZERO)){

            Set currentPlayerSet = player.getMatch().getSets().get(player.getMatch().getSets().size()-1);

            currentPlayerSet.setGame(currentPlayerSet.getGame()+1);
            resetScore(player, playerTwo);
        }

        // Win the game
        if(player.getMatch().getScore() == ScoreEnum.WINADVANTAGE){

            Set currentPlayerSet = player.getMatch().getSets().get(player.getMatch().getSets().size()-1);
            currentPlayerSet.setGame(currentPlayerSet.getGame()+1);

            resetScore(player, playerTwo);
        }

        // Deuce
        if (player.getMatch().getScore() == ScoreEnum.AVANTAGE && playerTwo.getMatch().getScore() == ScoreEnum.AVANTAGE){
            player.getMatch().setScore(ScoreEnum.QUARANTE);
            playerTwo.getMatch().setScore(ScoreEnum.QUARANTE);
        }

    }

    private void resetScore(Player player, Player playerTwo) {
        player.getMatch().setScore(ScoreEnum.ZERO);
        playerTwo.getMatch().setScore(ScoreEnum.ZERO);
    }

    @Override
    public void playerWinSet(Player player, Player playerTwo) {

        int currentSetIndex = player.getMatch().getSets().size() - 1;

        Set currentPlayerSet = player.getMatch().getSets().get(currentSetIndex);
        Set currentOpponentSet = playerTwo.getMatch().getSets().get(currentSetIndex);
        // Win game
        if (currentPlayerSet.getGame()>5 && currentPlayerSet.getGame() - currentOpponentSet.getGame() > 1){
            // reset game
            Set set = new Set();
            Set setTwo = new Set();
            set.setGame(0);
            setTwo.setGame(0);
            player.getMatch().getSets().add(set);
            playerTwo.getMatch().getSets().add(setTwo);
        }

    }

    public void playerWinMatch(Player player, Player playerTwo){

    }
}
