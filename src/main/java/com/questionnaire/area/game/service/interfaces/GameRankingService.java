package com.questionnaire.area.game.service.interfaces;

import com.questionnaire.area.game.dto.view.GameRankingAvgTimeView;
import com.questionnaire.area.game.dto.view.GameRankingBriefView;
import com.questionnaire.area.game.dto.view.GameRankingView;

import java.util.List;

/**
 * Created by ChaosFire on 1.5.2017 г
 */
public interface GameRankingService {

    List<GameRankingView> showTotalRanking();
    List<GameRankingBriefView> getPlayerRankingData(String username);
    List<GameRankingAvgTimeView> showAvgTimeRanking();
}