package examsys.first.domain;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Option extends BaseInfo implements Serializable  {
    private Long optionId;

    private Long itemId;

    private String optionContent;

    private Integer correctFlag;
}