package com.questionnaire.area.save.service;

import com.questionnaire.area.save.repository.SaveRepository;

/**
 * Created by ChaosFire on 1.5.2017 г
 */
public abstract class AbstractSaveServiceImpl {

    private final SaveRepository saveRepository;

    protected AbstractSaveServiceImpl(SaveRepository saveRepository) {
        this.saveRepository = saveRepository;
    }

    protected SaveRepository getSaveRepository() {
        return this.saveRepository;
    }
}