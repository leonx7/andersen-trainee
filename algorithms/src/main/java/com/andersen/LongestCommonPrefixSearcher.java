package com.andersen;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class LongestCommonPrefixSearcher {
    public static String getLongestPrefix(String[] strings){
        if(Objects.isNull(strings))
            throw new IllegalArgumentException();

        String result  = strings[0];

        for(int i = 1; i< strings.length; i++){
            while (strings[i].indexOf(result) != 0){
                result = result.substring(0, result.length()-1);
                if(result.isEmpty()){
                    return "";
                }
            }
        }
        return result;
    }

    public static String getLongestPrefix2(String[] strings){
        if(Objects.isNull(strings))
            throw new IllegalArgumentException();

        List<String> stringList = Arrays.asList(strings);

        String shortestString = stringList.stream().sorted(Comparator.comparingInt(String::length)).findFirst().orElse(null);

        for(int i = 0; i < strings.length; i++){
            if(strings[i].indexOf(shortestString) != 0){
                return "";
            }
        }
        return shortestString;
    }

}
