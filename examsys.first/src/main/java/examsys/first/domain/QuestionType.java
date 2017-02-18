package examsys.first.domain;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class QuestionType {
	private String code;
	private int qCount;
	private int correctFlag;
	private String name;
	private Long testpaperId;
}
