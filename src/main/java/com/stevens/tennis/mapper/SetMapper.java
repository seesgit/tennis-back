package com.stevens.tennis.mapper;

import com.stevens.tennis.model.dao.Set;
import com.stevens.tennis.model.dto.SetDto;
import org.mapstruct.Mapper;

@Mapper
public interface SetMapper {

//    SetMapper INSTANCE = Mappers.getMapper(SetMapper.class);

    SetDto setToSetDto(Set set);

    Set setDtoToSet(SetDto setDto);

}
