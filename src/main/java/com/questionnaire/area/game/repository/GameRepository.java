package com.questionnaire.area.game.repository;

import com.questionnaire.area.game.entity.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by ChaosFire on 23.4.2017 г
 */
@Repository
@SuppressWarnings("all")
public interface GameRepository extends JpaRepository<Game, Long> {

    @Query("SELECT gq.id " +
        "FROM Game AS g " +
        "INNER JOIN g.answeredQuestions AS gq " +
        "WHERE g.id = :id AND gq.questionLevel = :questionLevel")
    List<Long> findAnsweredQuestionsIdForGame(@Param("id") Long id, @Param("questionLevel") Byte questionLevel);
    @Modifying
    @Transactional
    @Query("UPDATE Game AS g " +
            "SET g.isFinished = true " +
            "WHERE g.id = :gameId")
    void finishGame(@Param("gameId") Long gameId);
    @Modifying
    @Transactional
    @Query("UPDATE Game AS g " +
            "SET g.totalTime = g.totalTime + :answerTime, g.currentQuestion = g.currentQuestion + 1 " +
            "WHERE g.id = :gameId")
    void updateGame(@Param("answerTime") long answerTime, @Param("gameId") long gameId);
    @Query("SELECT MAX(g.currentQuestion), g.totalTime, u.username " +
            "FROM Game AS g " +
            "INNER JOIN g.user AS u " +
            "WHERE g.isFinished = true " +
            "GROUP BY u.username " +
            "ORDER BY g.currentQuestion DESC, g.totalTime ASC")
    List<Object[]> findAllByCurrentQuestionRank();
    @Query(value = "SELECT users.username, MIN((games.total_time / (games.current_question - 1)) / 1000) AS average " +
                    "FROM games " +
                    "INNER JOIN users " +
                    "ON games.user_id = users.id " +
                    "WHERE games.is_finished = true " +
                    "GROUP BY users.username " +
                    "ORDER BY average",
        nativeQuery = true)
    List<Object[]> findAllByAverageAnswerTime();
}