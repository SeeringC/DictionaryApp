package com.example.translatetest1;

import Manager.DataManager;
import Manager.UIManager;
import Singleton.Singleton;

import java.util.Dictionary;
import java.util.Hashtable;

  public class MyDictionary extends Singleton<MyDictionary> implements CustomDictionary {
    public Dictionary<String, String> dic = new Hashtable<>();

    @Override
    public void addWordToDic(String word, String definition) {
        if (!isWordInDic(word)) {
            dic.put(word, definition);
            DataManager.getIns(DataManager.class).addWordtoSQL(word, definition);
            UIManager.getIns(UIManager.class).displayAlert("AddSuccess", word);
        } else {
            UIManager.getIns(UIManager.class).displayAlert("AddFailed", word);
        }
    }

    @Override
    public boolean isWordInDic(String word) {
        return dic.get(word) != null;
    }

    @Override
    public void deleteWordInDic(String word) {
        dic.remove(word);
        DataManager.getIns(DataManager.class).deleteWordSQL(word);
        UIManager.getIns(UIManager.class).displayAlert("DeleteWordSuccess", word);
    }

    @Override
    public String lookUpWordInDic(String word) {
        String searchedWordDefinition = null;
        try {
            searchedWordDefinition = dic.get(word);
        } catch (NullPointerException e) {
            UIManager.getIns(UIManager.class).displayAlert("SearchWordFailed", word);
        }
        return searchedWordDefinition;
    }

    @Override
    public void changeWordDefinition(String target, String definition) {
        dic.remove(target);
        dic.put(target, definition);
    }
}

