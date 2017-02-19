package examsys.first.domain;

import java.io.Serializable;

public class CategoryComposeKey implements Serializable {
    private String code;

    private Integer type;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}