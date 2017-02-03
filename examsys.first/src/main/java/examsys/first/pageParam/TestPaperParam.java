package examsys.first.pageParam;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TestPaperParam {
	private int spendSeconds;
	private List<QuestionParam> questions;
}
