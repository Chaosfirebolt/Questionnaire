package com.questionnaire.area.trueAnswer.repository;

import com.questionnaire.area.trueAnswer.entity.TrueAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by ChaosFire on 22.4.2017 г
 */
@Repository
public interface TrueAnswerRepository extends JpaRepository<TrueAnswer, Long> {

    @Query("SELECT ta " +
            "FROM TrueAnswer AS ta " +
            "WHERE ta.question.id = :questionId")
    TrueAnswer findOneByQuestionId(@Param("questionId") Long questionId);
}