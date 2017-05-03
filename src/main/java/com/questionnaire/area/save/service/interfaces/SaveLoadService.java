package com.questionnaire.area.save.service.interfaces;

import com.questionnaire.area.save.dto.view.SaveView;

import java.util.List;

/**
 * Created by ChaosFire on 1.5.2017 г
 */
public interface SaveLoadService {

    List<SaveView> getPlayerSaves(long userId);
    long loadGame(long saveId);
}