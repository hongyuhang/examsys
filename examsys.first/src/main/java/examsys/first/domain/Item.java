package examsys.first.domain;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class Item extends BaseInfo  {
    private Long itemId;

    private String code;

    private String itemContont;

    private Integer score;

    private Byte type;

    private String memo;
    
    private List<Option> options;

}