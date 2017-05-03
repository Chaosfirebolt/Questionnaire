package com.questionnaire.area.answer.repository;

import com.questionnaire.area.answer.entity.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by ChaosFire on 22.4.2017 г
 */
@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {
}