package examsys.first.domain;

import java.io.Serializable;

public class CategoryCompose extends CategoryComposeKey implements Serializable {
    private Integer count;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}