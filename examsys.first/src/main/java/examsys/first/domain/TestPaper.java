package examsys.first.domain;

import java.util.List;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class TestPaper {
    private Long testpaperId;

    private String categoryName;

    private Integer totalTime;

    private Integer spendTime;

    private Integer totalScore;

    private Integer score;

    private Integer itemCount;

    private Byte passFlag;

    private Integer passScore;

    private String testDatetime;

    private String userId;
    
    private Map<Integer, List<Item>> testContent;
}