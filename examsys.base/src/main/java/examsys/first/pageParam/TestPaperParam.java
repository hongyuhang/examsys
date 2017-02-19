package examsys.first.pageParam;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TestPaperParam implements Serializable {
	private int spendSeconds;
	private List<QuestionParam> questions;
}
