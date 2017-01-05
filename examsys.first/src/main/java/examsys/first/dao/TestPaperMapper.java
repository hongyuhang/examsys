package examsys.first.dao;

import examsys.first.domain.TestPaper;

public interface TestPaperMapper {
    int deleteByPrimaryKey(Long testpaperId);

    int insert(TestPaper record);

    int insertSelective(TestPaper record);

    TestPaper selectByPrimaryKey(Long testpaperId);

    int updateByPrimaryKeySelective(TestPaper record);

    int updateByPrimaryKey(TestPaper record);
    
}