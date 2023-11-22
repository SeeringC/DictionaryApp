package com.example.translatetest1;

import Manager.DataManager;
import Manager.UIManager;
import Singleton.Singleton;

import java.util.Dictionary;
import java.util.Hashtable;

public class MyDictionary extends Singleton<MyDictionary> implements CustomDictionary {

    public Dictionary<String, EnViWord> enToViDic = new Hashtable<>();

    @Override
    public <T extends Word> void loadWordFromSQL(T word) {
        if (word instanceof EnViWord) {
            enToViDic.put(word.getWordTarget(), (EnViWord) word);
        }
    }

    @Override
    public void addWordToDic(String word, String definition) {
        if (!isWordInDic(word)) {
            Cache.setCurrentEnViWordByTargetAndDefinition(word, definition);
            enToViDic.put(word, Cache.getCurrentEnViWord());

            DataManager.getIns(DataManager.class).addWordtoSQL(word, definition);
            UIManager.getIns(UIManager.class).displayAlert("AddSuccess", word);
        } else {
            UIManager.getIns(UIManager.class).displayAlert("AddFailed", word);
        }
    }

    @Override
    public boolean isWordInDic(String word) {
        return enToViDic.get(word) != null;
    }

    @Override
    public void deleteWordInDic(String word) {
        enToViDic.remove(word);
        DataManager.getIns(DataManager.class).deleteWordSQL(word);
        UIManager.getIns(UIManager.class).displayAlert("DeleteWordSuccess", word);
    }

    @Override
    public String lookUpWordInDic(String word) {
        if (word == null || enToViDic.get(word) == null) {
            UIManager.getIns(UIManager.class).displayAlert("SearchWordFailed", word);
            return null;
        }
        System.out.println("2 current word target: " + Cache.getCurrentEnViWordTarget());
        System.out.println("2 current word definition: " + Cache.getCurrentEnViWordDefinition());
        return enToViDic.get(word).getWordDefinition();
    }

    @Override
    public void changeWordDefinition(String target, String definition) {
        enToViDic.remove(target);
        Cache.setCurrentEnViWordByTargetAndDefinition(target, definition);
        enToViDic.put(target, Cache.getCurrentEnViWord());
    }
}

