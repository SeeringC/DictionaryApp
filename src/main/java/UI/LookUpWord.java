package UI;

import com.example.translatetest1.MyDictionary;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.ResourceBundle;

public class LookUpWord implements Initializable, UILayer {

    private ArrayList<String> tu = new ArrayList<>();
    @FXML
    private TextField input;
    @FXML
    private TextField output;

    @FXML
    private ListView<String> searchList;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        searchList.getItems().addAll(tu);
    }

    @Override
    public void onInit() {

    }

    @Override
    public void onCLose() {

    }

    public String handle_String(String s) {
        String xuongdong = "<br />";
        String delete_first = "<I><Q>";
        String delete_behind = "</Q></I>";
        String endl = "" + '\n';
        s = s.replaceAll(xuongdong, endl);
        s = s.replaceAll(delete_behind, "");
        s = s.replaceAll(delete_first, "");
        return s;
    }

    public ArrayList lookUpWord(KeyEvent event) {
        String t = input.getText();
        Enumeration<String> keys = MyDictionary.getIns(MyDictionary.class).enToViDic.keys();
        while (keys.hasMoreElements()) {
            String key = keys.nextElement();
            boolean check = true;
            for (int i = 0; i < t.length(); i++) {
                if (key.charAt(i) != t.charAt(i)) {
                    check = false;
                    break;
                }
            }
            if (check) {
                tu.add(key);
            }
        }
        return tu;
    }



}
