package examsys.first.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import examsys.first.domain.Item;

public interface ItemMapper {
    int deleteByPrimaryKey(Long itemId);

    int insert(Item record);

    int insertSelective(Item record);

    Item selectByPrimaryKey(Long itemId);

    int updateByPrimaryKeySelective(Item record);

    int updateByPrimaryKey(Item record);
    /**
     * 得到指定类型的随机数量的考题
     * @param type
     * @param count
     * @return
     */
    public List<Item> getRandomItems(@Param("type")Integer type, @Param("count")Integer count);
}