package com.questionnaire.io.newQuestions;

import com.questionnaire.area.answer.dto.bind.AnswerBind;
import com.questionnaire.area.question.dto.bind.QuestionBind;
import com.questionnaire.area.reference.dto.bind.AnswerReferenceBind;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ChaosFire on 27.4.2017 г
 */
public class QuestionsReader {

    private static final String PATH_NEW_QUESTIONS = "D:\\Desktop\\SoftUni\\Java_Web_MVC_Frameworks_Spring\\Final_Project\\questions.txt";

    public static List<QuestionBind> getNewQuestions() {
        File file = new File(PATH_NEW_QUESTIONS);
        List<QuestionBind> questions = new ArrayList<>();
        try (BufferedReader fileReader = new BufferedReader(new FileReader(file));) {
            String line = fileReader.readLine();
            StringBuilder sb = new StringBuilder();
            while (line != null) {
                sb.append(line);
                line = fileReader.readLine();
            }
            String[] questionsData = sb.toString().split("\\(break\\)");
            for (String dataString : questionsData) {
                String[] data = dataString.split(";");
                Map<String, String> elementsMap = mapElements(data);
                List<AnswerBind> answers = createAnswers(elementsMap);
                AnswerReferenceBind referenceBind = new AnswerReferenceBind(elementsMap.get("reference"));
                String questionText = elementsMap.get("questionText");
                byte level = Byte.parseByte(elementsMap.get("level"));

                QuestionBind questionBind = new QuestionBind(questionText, level, answers, referenceBind);
                questions.add(questionBind);
            }
            fileReader.close();
        } catch (IOException exc) {
            exc.printStackTrace();
        }
        return questions;
    }

    private static Map<String, String> mapElements(String[] data) {
        Map<String, String> map = new HashMap<>();
        for (String element : data) {
            String[] keyValuePair = element.trim().split(":\\s*");
            String key = keyValuePair[0].trim();
            String value = keyValuePair[1].trim();
            map.put(key, value);
        }
        return map;
    }

    private static List<AnswerBind> createAnswers(Map<String, String> elementsMap) {
        List<AnswerBind> answers = new ArrayList<>();
        answers.add(new AnswerBind(elementsMap.get("rightAnswer"), true));
        answers.add(new AnswerBind(elementsMap.get("firstWrongAnswer"), false));
        answers.add(new AnswerBind(elementsMap.get("secondWrongAnswer"), false));
        answers.add(new AnswerBind(elementsMap.get("thirdWrongAnswer"), false));
        return answers;
    }
}