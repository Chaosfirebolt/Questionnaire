package com.questionnaire.area.game.controller;

import com.questionnaire.area.game.dto.view.GameRankingAvgTimeView;
import com.questionnaire.area.game.dto.view.GameRankingBriefView;
import com.questionnaire.area.game.dto.view.GameRankingView;
import com.questionnaire.area.game.service.interfaces.GameRankingService;
import com.questionnaire.util.constants.Attribute;
import com.questionnaire.util.constants.View;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by ChaosFire on 2.5.2017 г
 */
@Controller
@RequestMapping("/ranking")
public class GameRankingController {

    private final GameRankingService gameRankingService;

    @Autowired
    public GameRankingController(GameRankingService gameRankingService) {
        this.gameRankingService = gameRankingService;
    }

    @GetMapping("/total")
    public String getTotalRanking(Model model) {
        List<GameRankingView> ranking = this.gameRankingService.showTotalRanking();
        model.addAttribute(Attribute.RANKING.getName(), ranking);

        final boolean isSearchPage = true;
        model.addAttribute(Attribute.SEARCH.getName(), isSearchPage);

        model.addAttribute(Attribute.VIEW.getName(), View.RANKS.getAddress());
        return View.BASIC.getAddress();
    }

    @GetMapping("/average-time")
    public String getAverageTimeRanking(Model model) {
        List<GameRankingAvgTimeView> ranking = this.gameRankingService.showAvgTimeRanking();
        model.addAttribute(Attribute.RANKING.getName(), ranking);

        model.addAttribute(Attribute.VIEW.getName(), View.RANKS_AVG.getAddress());
        return View.BASIC.getAddress();
    }

    @GetMapping("/user-data")
    public ResponseEntity<List<GameRankingBriefView>> getUsernameMatch(@RequestParam String username) {
        List<GameRankingBriefView> rankingViewList = this.gameRankingService.getPlayerRankingData(username);
        return new ResponseEntity<>(rankingViewList, HttpStatus.OK);
    }
}