package com.questionnaire.area.reference.repository;

import com.questionnaire.area.reference.entity.AnswerReference;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by ChaosFire on 22.4.2017 г
 */
@Repository
public interface AnswerReferenceRepository extends JpaRepository<AnswerReference, Long> {
}