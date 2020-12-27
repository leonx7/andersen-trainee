package com.andersen;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class RepeatsSearcher {
    public static Map<Integer, Integer> calculateRepeats(List<Integer> numbers) {
        Map<Integer, Integer> result = new HashMap<>();

        if (Objects.isNull(numbers) || numbers.isEmpty()) {
            return result;
        }

        for (Integer i : numbers) {
            if (result.containsKey(i)) {
                result.put(i, result.get(i) + 1);
            } else {
                result.put(i, 1);
            }
        }
        return result;
    }

    public static Map<Integer, Integer> calculateRepeats2(List<Integer> numbers){
        if (Objects.isNull(numbers)) {
            return new HashMap<>();
        }
         return numbers.stream().collect(HashMap::new, (map, i) -> map.put(i, map.containsKey(i) ? map.get(i) + 1 : 1), (map, res) -> res.putAll(map));
    }
}
