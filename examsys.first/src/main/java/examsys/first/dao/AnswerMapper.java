package examsys.first.dao;

import examsys.first.domain.Answer;

public interface AnswerMapper {
    int deleteByPrimaryKey(Long answerId);

    int insert(Answer record);

    int insertSelective(Answer record);

    Answer selectByPrimaryKey(Long answerId);

    int updateByPrimaryKeySelective(Answer record);

    int updateByPrimaryKey(Answer record);
}