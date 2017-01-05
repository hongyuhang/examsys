package examsys.first.dao;

import examsys.first.domain.Option;

public interface OptionMapper {
    int deleteByPrimaryKey(Long optionId);

    int insert(Option record);

    int insertSelective(Option record);

    Option selectByPrimaryKey(Long optionId);

    int updateByPrimaryKeySelective(Option record);

    int updateByPrimaryKey(Option record);
}