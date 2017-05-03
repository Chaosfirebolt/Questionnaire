package com.questionnaire.area.save.repository;

import com.questionnaire.area.save.entity.Save;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

/**
 * Created by ChaosFire on 29.4.2017 г
 */
@Repository
public interface SaveRepository extends JpaRepository<Save, Long> {

    @Query("SELECT s " +
            "FROM Save AS s " +
            "WHERE s.game.user.id = :userId " +
            "ORDER BY s.time DESC")
    List<Save> findSavesOfUser(@Param("userId") Long userId);
    Save findByGameId(Long gameId);
    @Transactional
    @Modifying
    @Query("UPDATE Save AS s " +
            "SET s.time = :now " +
            "WHERE s.game.id = :gameId")
    void updateByGameId(@Param("now") Date now, @Param("gameId") Long gameId);
    @Transactional
    void deleteByGameId(Long gameId);
}