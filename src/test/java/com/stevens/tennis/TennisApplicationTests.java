package com.stevens.tennis;

import com.stevens.tennis.model.dao.Match;
import com.stevens.tennis.model.dao.Player;
import com.stevens.tennis.model.dao.Set;
import com.stevens.tennis.model.enumeration.ScoreEnum;
import com.stevens.tennis.repository.PlayerDAO;
import com.stevens.tennis.repository.SetDAO;
import com.stevens.tennis.service.implementations.PlayerServiceImpl;
import com.stevens.tennis.service.implementations.RuleServiceImpl;
import com.stevens.tennis.service.interfaces.PlayerService;
import com.stevens.tennis.service.interfaces.RuleService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class TennisApplicationTests {

	@Mock
	PlayerDAO playerDao;

	@Mock
	SetDAO setDao;

	private RuleService ruleService;

	private PlayerService playerService;

	private static Player playerOne;
	private static Player playerTwo;

	private static Match matchOne;
	private static Match matchTwo;

	private static List<Set> setsOne;
	private static List<Set> setsTwo;

	@BeforeEach
	void creationEntites() {
		ruleService = new RuleServiceImpl();
		playerService = new PlayerServiceImpl(playerDao,ruleService);

		playerOne = new Player();
		playerOne.setId(1L);
		playerOne.setFirstName("player");
		playerOne.setName("one");

		playerTwo = new Player();
		playerTwo.setId(2L);
		playerTwo.setFirstName("player");
		playerTwo.setName("two");

		matchOne = new Match();
		matchOne.setId(1L);
		matchOne.setScore(ScoreEnum.ZERO);
		matchOne.setPlayer(playerOne);
		playerOne.setMatch(matchOne);

		matchTwo = new Match();
		matchTwo.setId(2L);
		matchTwo.setScore(ScoreEnum.ZERO);
		matchTwo.setPlayer(playerTwo);
		playerTwo.setMatch(matchTwo);

		Set set = new Set();
		set.setGame(0);
		Set setTwo = new Set();
		setTwo.setGame(0);
		setsOne = new ArrayList<>();
		setsTwo = new ArrayList<>();
		setsOne.add(set);
		setsTwo.add(setTwo);

		matchOne.setSets(setsOne);
		matchTwo.setSets(setsTwo);
	}

	@Test
	void testDeuces() {
		// Player two or player one score, so he has advantage too
		playerOne.getMatch().setScore(ScoreEnum.AVANTAGE);
		playerTwo.getMatch().setScore(ScoreEnum.AVANTAGE);

		ruleService.playerWinGame(playerOne,playerTwo);

		Assertions.assertEquals(ScoreEnum.QUARANTE,matchOne.getScore());
		Assertions.assertEquals(ScoreEnum.QUARANTE,matchTwo.getScore());
	}

	@Test
	void testWinGame() {
		playerOne.getMatch().setScore(ScoreEnum.AVANTAGE);
		playerTwo.getMatch().setScore(ScoreEnum.QUINZE);

		ruleService.playerWinGame(playerOne,playerTwo);

		Assertions.assertEquals(ScoreEnum.ZERO,matchOne.getScore());
		Assertions.assertEquals(ScoreEnum.ZERO,matchTwo.getScore());
		Assertions.assertEquals(1,matchOne.getSets().get(0).getGame());
		Assertions.assertEquals(0,matchTwo.getSets().get(0).getGame());
	}

	@Test
	void testWinGameAfterAdvantage() {
		playerOne.getMatch().setScore(ScoreEnum.WINADVANTAGE);
		playerTwo.getMatch().setScore(ScoreEnum.QUARANTE);

		ruleService.playerWinGame(playerOne,playerTwo);

		Assertions.assertEquals(ScoreEnum.ZERO,matchOne.getScore());
		Assertions.assertEquals(ScoreEnum.ZERO,matchTwo.getScore());
		Assertions.assertEquals(1,matchOne.getSets().get(0).getGame());
		Assertions.assertEquals(0,matchTwo.getSets().get(0).getGame());
	}

	@Test
	void testWinSet() {
		playerOne.getMatch().setScore(ScoreEnum.AVANTAGE);
		playerTwo.getMatch().setScore(ScoreEnum.QUINZE);
		playerOne.getMatch().getSets().get(0).setGame(5);

		ruleService.playerWinGame(playerOne,playerTwo);
		ruleService.playerWinSet(playerOne,playerTwo);

		Assertions.assertEquals(ScoreEnum.ZERO,matchOne.getScore());
		Assertions.assertEquals(ScoreEnum.ZERO,matchTwo.getScore());
		Assertions.assertEquals(6,matchOne.getSets().get(0).getGame());
		Assertions.assertEquals(0,matchTwo.getSets().get(0).getGame());
		Assertions.assertEquals(2,matchOne.getSets().size());
	}

	@Test
	void testPlayerScore() {
		playerOne.getMatch().setScore(ScoreEnum.ZERO);
		playerTwo.getMatch().setScore(ScoreEnum.ZERO);

		Mockito.when(playerDao.findById(Mockito.any())).thenReturn(java.util.Optional.ofNullable(playerOne)).thenReturn(java.util.Optional.ofNullable(playerTwo));
		Mockito.when(playerDao.save(Mockito.any())).thenReturn(playerOne).thenReturn(playerTwo);

		playerService.addPoint(playerOne.getId(),playerTwo.getId());

		Assertions.assertEquals(ScoreEnum.QUINZE,matchOne.getScore());
		Assertions.assertEquals(ScoreEnum.ZERO,matchTwo.getScore());
		Assertions.assertEquals(0,matchOne.getSets().get(0).getGame());
		Assertions.assertEquals(0,matchTwo.getSets().get(0).getGame());
		Assertions.assertEquals(1,matchOne.getSets().size());
		Assertions.assertEquals(1,matchTwo.getSets().size());
	}

}
