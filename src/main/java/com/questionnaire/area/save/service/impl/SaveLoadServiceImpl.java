package com.questionnaire.area.save.service.impl;

import com.questionnaire.area.save.dto.view.SaveView;
import com.questionnaire.area.save.entity.Save;
import com.questionnaire.area.save.repository.SaveRepository;
import com.questionnaire.area.save.service.AbstractSaveServiceImpl;
import com.questionnaire.area.save.service.interfaces.SaveLoadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ChaosFire on 1.5.2017 г
 */
@Service
public class SaveLoadServiceImpl extends AbstractSaveServiceImpl implements SaveLoadService {

    @Autowired
    public SaveLoadServiceImpl(SaveRepository saveRepository) {
        super(saveRepository);
    }

    @Override
    public List<SaveView> getPlayerSaves(long userId) {
        List<Save> playerSaves = super.getSaveRepository().findSavesOfUser(userId);
        List<SaveView> saveViewList = new ArrayList<>();
        for (Save save : playerSaves) {
            SaveView saveView = new SaveView(save.getId(), save.getTime());
            saveViewList.add(saveView);
        }
        return saveViewList;
    }

    @Override
    public long loadGame(long saveId) {
        return super.getSaveRepository().findOne(saveId).getGame().getId();
    }
}