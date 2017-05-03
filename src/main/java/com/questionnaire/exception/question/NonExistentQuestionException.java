package com.questionnaire.exception.question;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by ChaosFire on 3.5.2017 г
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "This question does not yet exist")
public class NonExistentQuestionException extends RuntimeException {
}