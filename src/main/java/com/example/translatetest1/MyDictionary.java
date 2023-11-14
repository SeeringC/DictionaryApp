package com.example.translatetest1;

import Singleton.Singleton;
import javafx.scene.control.Alert;

import java.util.Dictionary;
import java.util.*;

  class MyDictionary extends Singleton<MyDictionary> implements CustomDictionary {
    public Dictionary<String, String> dic = new Hashtable<>();
    DataBaseManager dataBaseM = DataBaseManager.getIns(DataBaseManager.class);

    @Override
    public void addWordToDic(String word, String definition) {
        if (isWordInDic(word)) {
            dic.put(word, definition);
            dataBaseM.addWordtoSQL(word, definition);
            displayAlert("AddSuccess", word);
        } else {
            displayAlert("AddFailed", word);
        }
    }

    @Override
    public boolean isWordInDic(String word) {
        return dic.get(word) != null;
    }

    @Override
    public void deleteWordInDic(String word, String definition) {
        if (isWordInDic(word)) {
            dic.remove(word);
            dataBaseM.deleteWordSQL(word);
            displayAlert("DeleteSuccess", word);
        }
        else {
            displayAlert("DeleteFailed", word);
        }
    }

    @Override
    public String lookUpWordInDic(String target) {
        if(isWordInDic(target)) {
            return dic.get(target);
        }
        return "404";
    }

    public void displayAlert(String type, String word) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        switch(type) {
            case "AddSuccess":
                alert.setTitle("Information dialog");
                alert.setContentText("Từ " + word + " đã được thêm thành công!");
                alert.setHeaderText("Thông báo");
                alert.showAndWait();
                break;

            case "AddFailed":
                alert.setTitle("Information dialog");
                alert.setContentText("Từ " + word + " đã tồn tại trong từ điển!");
                alert.setHeaderText("Thông báo");
                alert.showAndWait();
                break;

            case "DeleteSuccess":
                alert.setTitle("Information dialog");
                alert.setContentText("Từ " + word + " đã được xóa thành công!");
                alert.setHeaderText("Thông báo");
                alert.showAndWait();
                break;

            case "DeleteFailed":
                alert.setTitle("Information dialog");
                alert.setContentText("Từ " + word + " không tồn tại trong từ điển!");
                alert.setHeaderText("Thông báo");
                alert.showAndWait();
                break;

        }

    }
}

