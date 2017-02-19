package examsys.first.domain;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class QuestionType implements Serializable {
	private String code;
	private int qCount;
	private int correctFlag;
	private String name;
	private Long testpaperId;
}
