package com.questionnaire.area.user.repository;

import com.questionnaire.area.user.entity.AbstractUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by ChaosFire on 15.4.2017 г
 */
@Repository
public interface UserRepository<U extends AbstractUser> extends JpaRepository<U, Long> {

    U findOneByUsername(String username);
    U findOneByEmail(String email);
    @Query("SELECT u.username " +
            "FROM AbstractUser AS u " +
            "WHERE u.username LIKE CONCAT('%', :username, '%') ")
    List<String> findByUsernameMatch(@Param("username") String username);
}