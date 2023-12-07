package UI.Games.MCQsGameData;

import java.util.List;

public class Question {
    private String question;
    private String correctAnswer;
    private List<String> answerList;

    public <T> Question(String question, String correctAnswer, List<String> answerList) {
        this.question = question;
        this.correctAnswer = correctAnswer;
        this.answerList = answerList;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public List<String> getAnswerList() {
        return answerList;
    }

    public void setAnswerList(List<String> answerList) {
        this.answerList = answerList;
    }
}
