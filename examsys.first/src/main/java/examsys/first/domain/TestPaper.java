package examsys.first.domain;

import java.util.List;
import java.util.Map;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
@Data
public class TestPaper extends BaseInfo  {
    private Long testpaperId;

    private String code;
    
    private String categoryName;

    private Integer totalTime;

    private Integer spendTime;

    private Integer totalScore;

    private Integer score;

    private Integer itemCount;

    private Integer passFlag;

    private Integer passScore;

    private String testDatetime;

    private String userId;
    
    private Map<Integer, List<Item>> testContent;
}