package examsys.first.dao;

import examsys.first.domain.Question;

public interface QuestionMapper {
    int deleteByPrimaryKey(Long questionId);

    int insert(Question record);

    int insertSelective(Question record);

    Question selectByPrimaryKey(Long questionId);

    int updateByPrimaryKeySelective(Question record);

    int updateByPrimaryKey(Question record);
}