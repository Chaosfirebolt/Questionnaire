package com.questionnaire.area.reference.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * Created by ChaosFire on 22.4.2017 г
 */
public abstract class AbstractAnswerReferenceDto implements Serializable {

    private static final int MIN_REFERENCE_LENGTH = 5;

    @NotNull
    @Size(min = MIN_REFERENCE_LENGTH)
    private String url;

    protected AbstractAnswerReferenceDto() {
        super();
    }

    protected AbstractAnswerReferenceDto(String url) {
        this.setUrl(url);
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}