package com.questionnaire.area.save.service.impl;

import com.questionnaire.area.game.entity.Game;
import com.questionnaire.area.game.repository.GameRepository;
import com.questionnaire.area.save.dto.bind.SaveBind;
import com.questionnaire.area.save.entity.Save;
import com.questionnaire.area.save.repository.SaveRepository;
import com.questionnaire.area.save.service.AbstractSaveServiceImpl;
import com.questionnaire.area.save.service.interfaces.SaveGameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by ChaosFire on 1.5.2017 г
 */
@Service
public class SaveGameServiceImpl extends AbstractSaveServiceImpl implements SaveGameService {

    private final GameRepository gameRepository;

    @Autowired
    public SaveGameServiceImpl(SaveRepository saveRepository, GameRepository gameRepository) {
        super(saveRepository);
        this.gameRepository = gameRepository;
    }

    @Override
    public void saveGame(SaveBind saveBind) {
        SaveRepository saveRepository = super.getSaveRepository();
        Save save = saveRepository.findByGameId(saveBind.getGameId());
        if (save == null) {
            Game game = this.gameRepository.findOne(saveBind.getGameId());
            save = new Save(game, saveBind.getTime());
            saveRepository.saveAndFlush(save);
        } else {
            saveRepository.updateByGameId(saveBind.getTime(), saveBind.getGameId());
        }
    }

    @Override
    public void deleteSave(long gameId) {
        super.getSaveRepository().deleteByGameId(gameId);
    }
}