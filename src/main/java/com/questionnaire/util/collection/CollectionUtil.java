package com.questionnaire.util.collection;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by ChaosFire on 23.4.2017 г
 */
public class CollectionUtil {

    public static <T> List<T> shuffle(List<T> orderedList) {
        List<T> shuffledList = new ArrayList<>();
        Random random = new Random();
        int max = orderedList.size();
        while (max > 0) {
            int randomIndex = random.nextInt(max);
            T element = orderedList.remove(randomIndex);
            shuffledList.add(element);

            max--;
        }
        return shuffledList;
    }
}