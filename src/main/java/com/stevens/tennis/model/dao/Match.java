package com.stevens.tennis.model.dao;

import com.stevens.tennis.model.enumeration.ScoreEnum;

import javax.persistence.*;
import java.util.List;

@Entity
public class Match {
    @Id
    @GeneratedValue
    private Long id;

    @OneToOne(mappedBy = "match")
    Player player;

    @Enumerated(EnumType.STRING)
    ScoreEnum score;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "match_id", referencedColumnName = "id")
    List<Set> sets;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public ScoreEnum getScore() {
        return score;
    }

    public void setScore(ScoreEnum score) {
        this.score = score;
    }

    public List<Set> getSets() {
        return sets;
    }

    public void setSets(List<Set> game) {
        this.sets = game;
    }
}
