package com.example.translatetest1;

public class Cache {
    public static EnViWord currentEnViWord = new EnViWord("1", "1");

    public static EnViWord getCurrentEnViWord() {
        return currentEnViWord;
    }

    public static String getCurrentEnViWordTarget() {
        return currentEnViWord.getWordTarget();
    }

    public static String getCurrentEnViWordDefinition() {
        return currentEnViWord.getWordDefinition();
    }

    public static void setCurrentEnViWord(EnViWord EnViWord) {
        currentEnViWord = EnViWord;
    }

    public static void setCurrentEnViWordByTarget(String target) {
        currentEnViWord.setWordTarget(target);
    }

    public static void setCurrentEnViWordByTargetAndDefinition(String target, String definition) {
        currentEnViWord.setWordTarget(target);
        currentEnViWord.setWordDefinition(definition);
        if (definition == null) {
            currentEnViWord.setWordDefinition("No definition");
        }
    }
}
