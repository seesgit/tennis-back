package com.stevens.tennis.service.interfaces;

import com.stevens.tennis.model.dto.PlayerDto;

import java.util.List;

public interface PlayerService {

    List<PlayerDto> getPlayers();

    List<PlayerDto> addPoint(Long playerId,Long playerOpponent);

    PlayerDto savePlayer(PlayerDto newPlayer);

    PlayerDto updatePlayer(PlayerDto playerUpdate);
}
