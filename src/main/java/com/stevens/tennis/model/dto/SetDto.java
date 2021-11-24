package com.stevens.tennis.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SetDto {

    Long Id;
    Integer game;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public Integer getGame() {
        return game;
    }

    public void setGame(Integer game) {
        this.game = game;
    }
}
