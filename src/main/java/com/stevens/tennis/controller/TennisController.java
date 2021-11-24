package com.stevens.tennis.controller;

import com.stevens.tennis.model.dto.PlayerDto;
import com.stevens.tennis.service.interfaces.PlayerService;
import com.stevens.tennis.service.interfaces.RuleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.NotFoundException;
import java.util.List;

@RestController
//TODO : make a whiteList
@CrossOrigin(origins = "*")
@RequestMapping(path = "/tennis", produces = { "application/json" }, consumes = { "application/json" })
@ResponseBody
public class TennisController {

    PlayerService playerService;
    RuleService ruleService;

    TennisController(PlayerService playerService,RuleService ruleService){
        this.playerService = playerService;
        this.ruleService = ruleService;
    }

    @GetMapping("players")
    public ResponseEntity<List<PlayerDto> > getPlayers() {
        return new ResponseEntity<>(playerService.getPlayers(), HttpStatus.OK);

    }

    @PostMapping("player")
    public ResponseEntity<PlayerDto> savePlayer(@RequestBody PlayerDto newPlayer){
        PlayerDto player = playerService.savePlayer(newPlayer);
        if (player == null) {
            throw new NotFoundException();
        } else{
            return new ResponseEntity<>(player, HttpStatus.OK);
        }
    }

    @PostMapping("player/{playerIdWhoScore}/{playerOpponent}" )
    public ResponseEntity<List<PlayerDto>> playerScore(@PathVariable("playerIdWhoScore") Long playerId,
                                                 @PathVariable("playerOpponent") Long playerOpponent){
        List<PlayerDto> player = playerService.addPoint(playerId, playerOpponent);
        if (player == null) {
            throw new NotFoundException();
        } else{
            return new ResponseEntity<>(player, HttpStatus.OK);
        }
    }

}
