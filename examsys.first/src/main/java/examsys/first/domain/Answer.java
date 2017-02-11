package examsys.first.domain;

public class Answer extends BaseInfo  {
    private Long answerId;

    private Long questionId;

    private String answerContent;

    private Integer selected;

    private Integer correctFlag;

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

    public Integer getSelected() {
        return selected;
    }

    public void setSelected(Integer selected) {
        this.selected = selected;
    }

    public Integer getCorrectFlag() {
        return correctFlag;
    }

    public void setCorrectFlag(Integer correctFlag) {
        this.correctFlag = correctFlag;
    }
}