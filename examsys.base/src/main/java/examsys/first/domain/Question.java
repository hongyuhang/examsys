package examsys.first.domain;

import java.io.Serializable;
import java.util.List;

public class Question extends BaseInfo implements Serializable  {
    private Long questionId;

    private Long testpaperId;

    private String questionContent;
    
    private Integer questionType;

    private Integer seq;

    private Integer correctFlag;
    
    private List<Answer> answers;

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

    public Integer getCorrectFlag() {
        return correctFlag;
    }

    public void setCorrectFlag(Integer correctFlag) {
        this.correctFlag = correctFlag;
    }

	public List<Answer> getAnswers() {
		return answers;
	}

	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}

	public Integer getQuestionType() {
		return questionType;
	}

	public void setQuestionType(Integer questionType) {
		this.questionType = questionType;
	}
    
    
}