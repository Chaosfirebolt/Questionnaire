package com.questionnaire.area.save.entity;

import com.questionnaire.area.game.entity.Game;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by ChaosFire on 15.4.2017 г
 */
@Entity
@Table(name = "saves")
public class Save implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @OneToOne
    @JoinColumn(name = "game_id", referencedColumnName = "id", unique = true, nullable = false)
    private Game game;
    @Column(name = "time", nullable = false)
    private Date time;

    public Save() {
        super();
    }

    public Save(Game game, Date time) {
        this.setGame(game);
        this.setTime(time);
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Game getGame() {
        return this.game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Date getTime() {
        return this.time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}