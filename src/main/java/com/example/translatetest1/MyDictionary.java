package com.example.translatetest1;

import AlertDisplay.*;
import Manager.DataBaseManager;
import Singleton.Singleton;

import java.util.Dictionary;
import java.util.Hashtable;

public class MyDictionary extends Singleton<MyDictionary> implements CustomDictionary {

    public Dictionary<String, EnViWord> enToViDic = new Hashtable<>();
    private CustomAlert customAlert;

    @Override
    public <T extends Word> void loadWordFromSQL(T word) {
        if (word instanceof EnViWord) {
            enToViDic.put(word.getWordTarget(), (EnViWord) word);
        }
    }

    @Override
    public void addWordToDic(String word, String definition) {
        if (!isWordInDic(word)) {
            EnViWord enViWord = new EnViWord(word, definition);
            enToViDic.put(word, enViWord);

            DataBaseManager.getIns(DataBaseManager.class).addWordtoSQL(word, definition);

            setCustomAlert(new AddWordSuccessAlert());
            customAlert.displayAlert(word);

        } else {
            setCustomAlert(new AddWordFailedAlert());
            customAlert.displayAlert(word);
        }
    }

    @Override
    public boolean isWordInDic(String word) {
        return enToViDic.get(word) != null;
    }

    @Override
    public void deleteWordInDic(String word) {
        DataBaseManager.getIns(DataBaseManager.class).deleteWordSQL(word);
        enToViDic.remove(word);

        setCustomAlert(new DeleteWordSuccessAlert());
        customAlert.displayAlert(word);
    }

    @Override
    public String lookUpWordInDic(String word) {
        if (word == null || enToViDic.get(word) == null) {
            setCustomAlert(new SearchWordFailedAlert());
            customAlert.displayAlert(word);
            return null;
        }
        return enToViDic.get(word).getWordDefinition();

    }

    @Override
    public void changeWordDefinition(String target, String definition) {
        DataBaseManager.getIns(DataBaseManager.class).deleteWordSQL(target);
        DataBaseManager.getIns(DataBaseManager.class).addWordtoSQL(target, definition);
        enToViDic.remove(target);

        setCustomAlert(new ChangeWordDefinitionSuccessAlert());
        customAlert.displayAlert(target);

    }

    public void setCustomAlert(CustomAlert customAlert) {
        this.customAlert = customAlert;
    }
}

