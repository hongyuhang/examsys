package examsys.first.domain;

public class Question extends BaseInfo  {
    private Long questionId;

    private Long testpaperId;

    private String questionContent;

    private Integer seq;

    private Byte correctFlag;

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public Long getTestpaperId() {
        return testpaperId;
    }

    public void setTestpaperId(Long testpaperId) {
        this.testpaperId = testpaperId;
    }

    public String getQuestionContent() {
        return questionContent;
    }

    public void setQuestionContent(String questionContent) {
        this.questionContent = questionContent == null ? null : questionContent.trim();
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public Byte getCorrectFlag() {
        return correctFlag;
    }

    public void setCorrectFlag(Byte correctFlag) {
        this.correctFlag = correctFlag;
    }
}