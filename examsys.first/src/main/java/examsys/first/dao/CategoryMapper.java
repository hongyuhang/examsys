package examsys.first.dao;

import org.springframework.stereotype.Repository;

import examsys.first.domain.Category;
@Repository
public interface CategoryMapper {
    int deleteByPrimaryKey(String code);

    int insert(Category record);

    int insertSelective(Category record);

    Category selectByPrimaryKey(String code);

    int updateByPrimaryKeySelective(Category record);

    int updateByPrimaryKey(Category record);
}