package com.example.translatetest1;

import java.util.Objects;

public class Word {
    private String wordTarget;
    private String wordExplain;

    /**
     * Constructor new Word.
     *
     * @param wordTarget  English word
     * @param wordExplain Vietnamese definition
     */
    public Word(String wordTarget, String wordExplain) {
        this.wordTarget = wordTarget;
        this.wordExplain = wordExplain;
    }

    /**
     * Get the English word.
     *
     * @return English word
     */
    public String getWordTarget() {
        return wordTarget;
    }

    /**
     * Get definition of word.
     *
     * @return Vietnamese definition of word
     */
    public String getWordDefinition() {
        return wordExplain;
    }

    /**
     * Set Vietnamese definition of the word.
     *
     * @param wordExplain Vietnamese definition
     */

    public void setWordDefinition(String wordExplain) {
        this.wordExplain = wordExplain;
    }

    public void setWordTarget(String wordTarget) {
        this.wordTarget = wordTarget;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Word word = (Word) o;
        return Objects.equals(wordTarget, word.wordTarget);
    }

    @Override
    public int hashCode() {
        return Objects.hash(wordTarget);
    }
}
