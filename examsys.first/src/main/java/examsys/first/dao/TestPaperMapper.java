package examsys.first.dao;

import org.apache.ibatis.annotations.Param;

import examsys.first.domain.TestPaper;

public interface TestPaperMapper {
    int deleteByPrimaryKey(Long testpaperId);

    int insert(TestPaper record);

    int insertSelective(TestPaper record);

    TestPaper selectByPrimaryKey(Long testpaperId);

    int updateByPrimaryKeySelective(TestPaper record);

    int updateByPrimaryKey(TestPaper record);
    
    long getIdByUserIdAndCName(@Param("userId")String userId, @Param("categoryName")String categoryName);
    
    TestPaper getGrade(@Param("userId")String userId, @Param("categoryCode")String categoryCode);
}