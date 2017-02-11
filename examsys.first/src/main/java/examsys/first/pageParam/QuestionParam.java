package examsys.first.pageParam;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuestionParam {
	private int index;
	private String questionType;
	private String questionCode;
	private List<String> selected;
}
