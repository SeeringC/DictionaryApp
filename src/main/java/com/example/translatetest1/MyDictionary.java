package com.example.translatetest1;

import Singleton.Singleton;
import javafx.scene.control.Alert;

import java.util.Dictionary;
import java.util.*;

  class MyDictionary extends Singleton<MyDictionary> {
    public static Dictionary<String, String> dic = new Hashtable<>();
    public static void addWordtodic(String word, String definition) {
        if (isWordInDic(word)) {
            dic.put(word, definition);
            DataBaseManager.addWordtoSQL(word, definition);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information dialog");
            alert.setContentText("Từ " + word + " đã được thêm thành công!");
            alert.setHeaderText("Thông báo");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information dialog");
            alert.setContentText("Từ " + word + " đã tồn tại trong từ điển!");
            alert.setHeaderText("Thông báo");
            alert.showAndWait();
        }
    }

    public static boolean isWordInDic(String word) {
        if(dic.get(word) != null) {
            System.out.println("Word already in dic");
            return true;
        }
        return false;
    }

    public static void deleteWordInDic(String word, String definition) {
        if (isWordInDic(word)) {
            dic.remove(word);
        }
        else {
            System.out.println("Từ không tồn tại trong từ điển");
        }
    }
    public static String lookUpWordInDic(String target) {
        if(isWordInDic(target)) {
            return dic.get(target);
        }
        return "404";
    }
}
