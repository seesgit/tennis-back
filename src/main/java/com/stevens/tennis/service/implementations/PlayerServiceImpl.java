package com.stevens.tennis.service.implementations;

import com.stevens.tennis.mapper.PlayerMapper;
import com.stevens.tennis.model.dao.Player;
import com.stevens.tennis.model.dto.MatchDto;
import com.stevens.tennis.model.dto.PlayerDto;
import com.stevens.tennis.model.dto.SetDto;
import com.stevens.tennis.model.enumeration.ScoreEnum;
import com.stevens.tennis.repository.PlayerDAO;
import com.stevens.tennis.service.interfaces.PlayerService;
import com.stevens.tennis.service.interfaces.RuleService;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import javax.ws.rs.NotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class PlayerServiceImpl implements PlayerService {

    private PlayerDAO playerDAO;

    private RuleService ruleService;

    private final PlayerMapper playerMapper = Mappers.getMapper( PlayerMapper.class);

    public PlayerServiceImpl(PlayerDAO playerDAO,RuleService ruleService){
        this.playerDAO = playerDAO;
        this.ruleService = ruleService;
    }

    @Override
    public List<PlayerDto> getPlayers() {
        List<PlayerDto> playersToReturn = new ArrayList<PlayerDto>();
        Iterable<Player> all = playerDAO.findAll();

        List<Player> players = StreamSupport
                .stream(all.spliterator(), false)
                .collect(Collectors.toList());

        for (Player player:players) {
            playersToReturn.add(playerMapper.playerToPlayerDto(player));
        }
        return playersToReturn;
    }

    @Override
    public List<PlayerDto> addPoint(Long playerId, Long playerOpponent) {
        List<PlayerDto> playersToReturn = new ArrayList<PlayerDto>();

        Player playerWhoScore = playerDAO.findById(playerId).orElse(null);
        Player opponent = playerDAO.findById(playerOpponent).orElse(null);

        if (playerWhoScore == null || opponent == null) {
            throw new NotFoundException();
        }

        switch(playerWhoScore.getMatch().getScore()){
            case ZERO:
                playerWhoScore.getMatch().setScore(ScoreEnum.QUINZE);
                break;
            case QUINZE:
                playerWhoScore.getMatch().setScore(ScoreEnum.TRENTE);
                break;
            case TRENTE:
                playerWhoScore.getMatch().setScore(ScoreEnum.QUARANTE);
                break;
            case QUARANTE:
                playerWhoScore.getMatch().setScore(ScoreEnum.AVANTAGE);
                break;
            case AVANTAGE:
                playerWhoScore.getMatch().setScore(ScoreEnum.WINADVANTAGE);
                break;
        }

        ruleService.playerWinGame(playerWhoScore,opponent);
        ruleService.playerWinSet(playerWhoScore,opponent);

        Player savePlayer = playerDAO.save(playerWhoScore);
        Player saveOpponent = playerDAO.save(opponent);

        playersToReturn.add(playerMapper.playerToPlayerDto(savePlayer));
        playersToReturn.add(playerMapper.playerToPlayerDto(saveOpponent));

        return playersToReturn;
    }

    @Override
    public PlayerDto savePlayer(PlayerDto newPlayer) {
        // Init Player Scores if new player
        if(newPlayer.getId() == null){
            MatchDto match = new MatchDto();
            SetDto setDto = new SetDto();

            match.setScore("ZERO");
            setDto.setGame(0);
            match.setSets(Arrays.asList(setDto));

            newPlayer.setMatch(match);
        }
        Player player = playerDAO.save(playerMapper.playerDtoToPlayer(newPlayer));
        return playerMapper.playerToPlayerDto(player);
    }

    @Override
    public PlayerDto updatePlayer(PlayerDto playerUpdate) {
        Player player = playerDAO.save(playerMapper.playerDtoToPlayer(playerUpdate));
        return playerMapper.playerToPlayerDto(player);
    }
}
