package examsys.first.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import examsys.first.domain.Category;
@Repository
public interface CategoryMapper {
    int deleteByPrimaryKey(String code);

    int insert(Category record);

    int insertSelective(Category record);

    Category selectByPrimaryKey(String code);
    
    List<Category> selectAll();

    int updateByPrimaryKeySelective(Category record);

    int updateByPrimaryKey(Category record);
}