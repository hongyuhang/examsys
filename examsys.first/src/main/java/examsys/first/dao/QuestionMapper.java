package examsys.first.dao;

import org.apache.ibatis.annotations.Param;

import examsys.first.domain.Question;

public interface QuestionMapper {
    int deleteByPrimaryKey(Long questionId);

    int insert(Question record);

    int insertSelective(Question record);

    Question selectByPrimaryKey(Long questionId);

    int updateByPrimaryKeySelective(Question record);

    int updateByPrimaryKey(Question record);
    
    long getIdByTestPaperIdAndSeq(@Param("testPaperId")Long testPaperId, @Param("seq")Integer seq);
    
}