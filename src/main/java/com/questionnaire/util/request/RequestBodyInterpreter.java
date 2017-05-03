package com.questionnaire.util.request;

import com.questionnaire.area.answer.dto.bind.AnswerBind;
import com.questionnaire.util.constants.PossibleAnswer;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by ChaosFire on 23.4.2017 г
 */
public class RequestBodyInterpreter {

    private Map<String, String> requestDataMap;

    public RequestBodyInterpreter(String requestBody) {
        this.setRequestDataMap(this.mapData(requestBody));
    }

    public List<AnswerBind> getPossibleAnswers() {
        Map<String, String> map = this.getRequestDataMap();

        List<AnswerBind> answerBindList = new ArrayList<>();
        answerBindList.add(new AnswerBind(this.manipulateString(map.get(PossibleAnswer.RIGHT.getAttribute())), true));
        answerBindList.add(new AnswerBind(this.manipulateString(map.get(PossibleAnswer.FIRST_WRONG.getAttribute())), false));
        answerBindList.add(new AnswerBind(this.manipulateString(map.get(PossibleAnswer.SECOND_WRONG.getAttribute())), false));
        answerBindList.add(new AnswerBind(this.manipulateString(map.get(PossibleAnswer.THIRD_WRONG.getAttribute())), false));

        return answerBindList;
    }

    private String manipulateString(String string) {
        Pattern pattern = Pattern.compile("\\+");
        Matcher matcher = pattern.matcher(string);

        StringBuilder sb = new StringBuilder(string);
        while (matcher.find()) {
            int index = matcher.start();
            int previous = index - 1;
            int next = index + 1;

            if (previous >= 0 && next <= string.length() - 1) {
                char prevChar = string.charAt(previous);
                char nextChar = string.charAt(next);
                if (Character.isDigit(prevChar) && Character.isDigit(nextChar)) {
                    continue;
                }
            }

            sb.setCharAt(index, ' ');
        }
        return sb.toString();
    }

    private Map<String, String> getRequestDataMap() {
        return Collections.unmodifiableMap(this.requestDataMap);
    }

    private Map<String, String> mapData(String requestBody) {
        String[] keyValuePairs = requestBody.split("&");
        Map<String, String> dataMap = new HashMap<>();
        for (String keyValuePair : keyValuePairs) {
            String[] pair = keyValuePair.split("=");
            String key = pair[0];
            String value = null;
            if (pair.length == 2) {
                value = pair[1];
            }
            dataMap.put(key, value);
        }
        return dataMap;
    }

    private void setRequestDataMap(Map<String, String> requestDataMap) {
        this.requestDataMap = requestDataMap;
    }
}