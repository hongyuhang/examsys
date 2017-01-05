package examsys.first.domain;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class Category extends BaseInfo {
    private String code;

    private String name;

    private Integer score;

    private Integer time;

    private Integer passScore;
    
    private List<CategoryCompose>  composes;
    
}