package com.example.translatetest1;

public interface CustomDictionary {

    <T extends Word> void loadWordFromSQL(T word);
    void addWordToDic(String word, String definition);

    boolean isWordInDic(String word);

    void deleteWordInDic(String word);

    String lookUpWordInDic(String target);

    void changeWordDefinition(String target, String definition);
}

