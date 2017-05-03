package com.questionnaire.area.question.repository;

import com.questionnaire.area.question.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by ChaosFire on 22.4.2017 г
 */
@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {

    @Query("SELECT q.id " +
            "FROM Question AS q " +
            "WHERE q.isValidated = true AND q.questionLevel = :level")
    List<Long> findValidQuestionsId(@Param("level") Byte level);
    @Transactional
    @Modifying
    @Query("UPDATE Question AS q " +
            "SET q.isValidated = true " +
            "WHERE q.id = :id")
    void validateQuestion(@Param("id") Long id);
    @Query("SELECT q " +
            "FROM Question AS q " +
            "WHERE q.isValidated = false AND q.author.id <> :id")
    List<Question> findNotValidated(@Param("id") Long id);
    @Transactional
    @Modifying
    @Query("UPDATE Question AS q " +
            "SET q.isValidated = true " +
            "WHERE q.author.id = :authorId")
    void validateOwnQuestions(@Param("authorId") Long id);
}