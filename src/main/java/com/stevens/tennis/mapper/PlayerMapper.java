package com.stevens.tennis.mapper;

import com.stevens.tennis.model.dao.Player;
import com.stevens.tennis.model.dto.PlayerDto;
import org.mapstruct.Mapper;

@Mapper(uses = MatchMapper.class)
public interface PlayerMapper {


    PlayerDto playerToPlayerDto(Player player);

    Player playerDtoToPlayer(PlayerDto player);

}
