package com.stevens.tennis.mapper;

import com.stevens.tennis.model.dao.Match;
import com.stevens.tennis.model.dto.MatchDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = SetMapper.class)
public interface MatchMapper {

    @Mapping(source="match.score.label", target="score")
    public abstract MatchDto matchToMatchDto(Match match);

    @Mapping(source="matchDto.score", target="score")
    public abstract Match matchDtoToMatch(MatchDto matchDto);


}
