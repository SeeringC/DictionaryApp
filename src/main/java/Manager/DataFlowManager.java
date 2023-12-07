package Manager;

import Singleton.Singleton;
import UI.Games.MCQsGameData.Question;
import com.example.translatetest1.EnViWord;

import java.util.ArrayList;
import java.util.List;

public class DataFlowManager extends Singleton<DataFlowManager> {
    public EnViWord currentEnViWord = new EnViWord("", "");

    public EnViWord getCurrentEnViWord() {
        return currentEnViWord;
    }

    public String getCurrentEnViWordTarget() {
        return currentEnViWord.getWordTarget();
    }

    public String getCurrentEnViWordDefinition() {
        return currentEnViWord.getWordDefinition();
    }

    public void setCurrentEnViWord(EnViWord EnViWord) {
        currentEnViWord = EnViWord;
    }

    public void setCurrentEnViWordByTarget(String target) {
        currentEnViWord.setWordTarget(target);
    }

    public void setCurrentEnViWordByTargetAndDefinition(String target, String definition) {
        currentEnViWord.setWordTarget(target);
        currentEnViWord.setWordDefinition(definition);
        if (definition == null) {
            currentEnViWord.setWordDefinition("No definition");
        }
    }

    ///////////////////////////////////////////////////////////////////////////////////

    public List<Question> questions = new ArrayList<>();
}
