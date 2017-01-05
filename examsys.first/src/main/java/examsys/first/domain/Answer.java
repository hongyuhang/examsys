package examsys.first.domain;

public class Answer {
    private Long answerId;

    private Long questionId;

    private String answerContent;

    private Byte selected;

    private Byte correctFlag;

    public Long getAnswerId() {
        return answerId;
    }

    public void setAnswerId(Long answerId) {
        this.answerId = answerId;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public String getAnswerContent() {
        return answerContent;
    }

    public void setAnswerContent(String answerContent) {
        this.answerContent = answerContent == null ? null : answerContent.trim();
    }

    public Byte getSelected() {
        return selected;
    }

    public void setSelected(Byte selected) {
        this.selected = selected;
    }

    public Byte getCorrectFlag() {
        return correctFlag;
    }

    public void setCorrectFlag(Byte correctFlag) {
        this.correctFlag = correctFlag;
    }
}