package com.questionnaire.area.game.controller;

import com.questionnaire.area.game.dto.bind.GameBind;
import com.questionnaire.area.game.dto.bind.answer.GivenAnswerBind;
import com.questionnaire.area.game.dto.view.GameView;
import com.questionnaire.area.game.service.interfaces.GameService;
import com.questionnaire.area.question.dto.view.QuestionView;
import com.questionnaire.area.question.service.interfaces.QuestionGameService;
import com.questionnaire.area.save.dto.bind.SaveBind;
import com.questionnaire.area.save.service.interfaces.SaveGameService;
import com.questionnaire.area.trueAnswer.service.TrueAnswerService;
import com.questionnaire.area.user.entity.AbstractUser;
import com.questionnaire.util.constants.Attribute;
import com.questionnaire.util.constants.View;
import com.questionnaire.util.redirection.Redirection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Created by ChaosFire on 29.4.2017 г
 */
@Controller
@RequestMapping("/games")
public class GameController {

    private final GameService gameService;
    private final QuestionGameService questionGameService;
    private final TrueAnswerService trueAnswerService;
    private final SaveGameService saveGameService;

    @Autowired
    public GameController(GameService gameService, QuestionGameService questionGameService,
                          TrueAnswerService trueAnswerService, SaveGameService saveGameService) {
        this.gameService = gameService;
        this.questionGameService = questionGameService;
        this.trueAnswerService = trueAnswerService;
        this.saveGameService = saveGameService;
    }

    @GetMapping("/new-game")
    public String startNewGame() {
        String username = ((AbstractUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        GameBind gameBind = new GameBind(username);
        GameView gameView = this.gameService.startNewGame(gameBind);

        long newGameId = gameView.getGameId();
        final String gamePage = "/games/" + newGameId + "/question";
        return Redirection.redirect(gamePage);
    }

    @GetMapping("/{gameId}/question")
    public String getGamePage(@PathVariable long gameId, RedirectAttributes redirectAttributes) {
        GameView gameView = this.gameService.getGame(gameId);
        this.saveGameService.deleteSave(gameId);

        byte questionLevel = this.calculateQuestionLevel(gameView.getCurrentQuestion());
        QuestionView questionView = this.questionGameService.chooseNextQuestion(questionLevel, gameView.getGameId());
        redirectAttributes.addFlashAttribute(Attribute.QUESTION.getName(), questionView);

        final String gameQuestionPage = "/games/" + gameId + "/question/" + questionView.getId();
        return Redirection.redirect(gameQuestionPage);
    }

    private byte calculateQuestionLevel(byte currentQuestion) {
        int remainder = currentQuestion % 10;
        byte questionLevel;
        if (remainder == 0) {
            questionLevel = (byte) (currentQuestion / 10);
        } else {
            questionLevel = (byte) ((currentQuestion / 10) + 1);
        }
        return questionLevel;
    }

    @GetMapping("/{gameId}/question/{questionId}")
    public String answerQuestionPage(Model model) {
        model.addAttribute(Attribute.VIEW.getName(), View.GAME.getAddress());
        return View.BASIC.getAddress();
    }

    @PostMapping("/{gameId}/question/{questionId}")
    public String verifyAnswer(@PathVariable long gameId, @ModelAttribute GivenAnswerBind givenAnswer) {
        boolean isAnswerTrue = this.trueAnswerService.verifyAnswer(givenAnswer.getQuestionId(), givenAnswer.getAnswerId());
        if (!isAnswerTrue) {
            this.gameService.finishGame(gameId);
            final String gameOverPage = "/games/game-over";
            return Redirection.redirect(gameOverPage);
        }

        final long placeholderTime = 40_000;
        this.gameService.updateGame(placeholderTime, gameId);
        this.gameService.addQuestionToAnswered(gameId, givenAnswer.getQuestionId());
        final String gamePage = "/games/" + gameId + "/save-or-continue";
        return Redirection.redirect(gamePage);
    }

    @GetMapping("/game-over")
    public String gameOverPage(Model model) {
        model.addAttribute(Attribute.VIEW.getName(), View.WRONG_ANSWER.getAddress());
        return View.BASIC.getAddress();
    }

    @GetMapping("/{gameId}/save-or-continue")
    public String getChoicePage(Model model) {
        model.addAttribute(Attribute.VIEW.getName(), View.GAME_CHOICE.getAddress());
        return View.BASIC.getAddress();
    }

    @PostMapping("/{gameId}/save-or-continue")
    public String choiceAction(@PathVariable long gameId, @RequestParam String action, @ModelAttribute SaveBind saveBind) {
        String saveButtonValue = "save";
        String continueButtonValue = "continue";

        action = action.toLowerCase();
        final String homePage = "/";
        if (action.equals(saveButtonValue)) {
            saveBind.setGameId(gameId);
            this.saveGameService.saveGame(saveBind);
            return Redirection.redirect(homePage);
        } else if (action.equals(continueButtonValue)) {
            final String questionPage = "/games/" + gameId + "/question";
            return Redirection.redirect(questionPage);
        }
        return Redirection.redirect(homePage);
    }
}