package com.questionnaire.area.game.service.impl;

import com.questionnaire.area.game.dto.view.GameRankingAvgTimeView;
import com.questionnaire.area.game.dto.view.GameRankingBriefView;
import com.questionnaire.area.game.dto.view.GameRankingView;
import com.questionnaire.area.game.repository.GameRepository;
import com.questionnaire.area.game.service.AbstractGameServiceImpl;
import com.questionnaire.area.game.service.interfaces.GameRankingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by ChaosFire on 1.5.2017 г
 */
@Service
public class GameRankingServiceImpl extends AbstractGameServiceImpl implements GameRankingService {

    @Autowired
    public GameRankingServiceImpl(GameRepository gameRepository) {
        super(gameRepository);
    }

    @Override
    public List<GameRankingView> showTotalRanking() {
        List<Object[]> rankingDataList = super.getGameRepository().findAllByCurrentQuestionRank();
        List<GameRankingView> ranking = new ArrayList<>();
        for (int index = 0; index < rankingDataList.size(); index++) {
            Object[] data = rankingDataList.get(index);

            long position = index + 1;
            byte lastAnsweredQuestion = (byte) ((byte) data[0] - 1);
            BigInteger millis = (BigInteger) data[1];
            long milliseconds = millis.longValue();
            String totalTime = this.convertMillisecondsToTime(milliseconds);
            String username = data[2].toString();

            GameRankingView view = new GameRankingView(position, username, lastAnsweredQuestion, totalTime);
            ranking.add(view);
        }
        return ranking;
    }

    @Override
    public List<GameRankingBriefView> getPlayerRankingData(String username) {
        List<Object[]> playerRankData = super.getGameRepository().findAllByCurrentQuestionRankNameMatch(username);
        List<GameRankingBriefView> rankingViewList = new ArrayList<>();
        for (Object[] data : playerRankData) {
            byte lastAnsweredQuestion = (byte) ((byte) data[0] - 1);
            long milliseconds = (long) data[1];
            String totalTime = this.convertMillisecondsToTime(milliseconds);
            String user = data[2].toString();

            GameRankingBriefView view = new GameRankingBriefView(user, lastAnsweredQuestion, totalTime);
            rankingViewList.add(view);
        }
        return rankingViewList;
    }

    @Override
    public List<GameRankingAvgTimeView> showAvgTimeRanking() {
        List<Object[]> rankingDataList = super.getGameRepository().findAllByAverageAnswerTime();
        List<GameRankingAvgTimeView> ranking = new ArrayList<>();
        for (int index = 0; index < rankingDataList.size(); index++) {
            Object[] data = rankingDataList.get(index);

            long position = index + 1;
            String username = data[0].toString();
            BigDecimal seconds = new BigDecimal(data[1].toString());
            String avgAnswerTime = this.convertMillisecondsToTime(seconds);

            GameRankingAvgTimeView view = new GameRankingAvgTimeView(position, username, avgAnswerTime);
            ranking.add(view);
        }
        return ranking;
    }

    private String convertMillisecondsToTime(BigDecimal seconds) {
        int intPart = seconds.intValue();
        int decimalPart = seconds.subtract(new BigDecimal(intPart))
                .multiply(new BigDecimal(1_000))
                .setScale(3, RoundingMode.HALF_UP)
                .intValue();
        return String.format("%02d.%03d sec", intPart, decimalPart);
    }

    private String convertMillisecondsToTime(long milliseconds) {
        long oneSecondToMillis = 1_000;

        long hours = TimeUnit.MILLISECONDS.toHours(milliseconds);
        long remainderMillis = milliseconds - (hours * 3_600 * oneSecondToMillis);

        long minutes = TimeUnit.MILLISECONDS.toMinutes(remainderMillis);
        remainderMillis = remainderMillis - (minutes * 60 * oneSecondToMillis);

        long seconds = TimeUnit.MILLISECONDS.toSeconds(remainderMillis);

        remainderMillis = remainderMillis - (seconds * oneSecondToMillis);
        return String.format("%02dh:%02dm:%02ds.%03dms", hours, minutes, seconds, remainderMillis);
    }
}