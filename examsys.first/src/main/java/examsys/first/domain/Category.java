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
    
    private String description;
    
    public void setDescription(String description) {
    	this.description = "考试时间：" + time + "分钟；考试总分数:" + score + "；合格分数：" + passScore;
    }
    
    public String getDescription() {
    	
    	return description;
    }
    
}