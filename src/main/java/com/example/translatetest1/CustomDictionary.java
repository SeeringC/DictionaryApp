package com.example.translatetest1;

import java.util.Dictionary;

public interface CustomDictionary {

    void addWordToDic(String word, String definition);

    boolean isWordInDic(String word);

    void deleteWordInDic(String word, String definition);

    String lookUpWordInDic(String target);
}
