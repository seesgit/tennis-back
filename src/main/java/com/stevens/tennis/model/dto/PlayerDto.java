package com.stevens.tennis.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlayerDto {

    Long Id;
    String name;
    String firstname;
    MatchDto match;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstName() {
        return firstname;
    }

    public void setFirstName(String firstname) {
        this.firstname = firstname;
    }

    public MatchDto getMatch() {
        return match;
    }

    public void setMatch(MatchDto match) {
        this.match = match;
    }
}
