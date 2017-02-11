package examsys.first.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Option extends BaseInfo  {
    private Long optionId;

    private Long itemId;

    private String optionContent;

    private Integer correctFlag;
}