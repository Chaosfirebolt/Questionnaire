package com.questionnaire.area.save.service.interfaces;

import com.questionnaire.area.save.dto.bind.SaveBind;

/**
 * Created by ChaosFire on 1.5.2017 г
 */
public interface SaveGameService {

    void saveGame(SaveBind saveBind);
    void deleteSave(long gameId);
}