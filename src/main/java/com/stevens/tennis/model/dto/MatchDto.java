package com.stevens.tennis.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MatchDto {

    Long Id;
    String score;
    List<SetDto> sets;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public List<SetDto> getSets() {
        return sets;
    }

    public void setSets(List<SetDto> sets) {
        this.sets = sets;
    }
}
