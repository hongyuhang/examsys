package examsys.first.domain;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class Item extends BaseInfo implements Serializable {
    private Long itemId;

    private String code;

    private String itemContont;

    private Integer score;

    private Byte type;

    private String memo;
    
    private List<Option> options;

}