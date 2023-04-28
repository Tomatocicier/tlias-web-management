package com.it.mapper;

import com.it.pojo.Dept;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DeptMapper {
    /**
     *查询全部部门
     */
    @Select("select * from dept")
    List<Dept> list();

    /**
     * 根据ID删除部门
     * @param id
     */
    @Delete("delete from dept where id = #{id}")
    void deleteById(Integer id);

    /**
     * 添加部门
     * @param dept
     */
    @Insert("insert into dept (name,create_time,update_time) " +
            "values (#{name},#{createTime},#{updateTime})")
    void add(Dept dept);


    /**
     * 修改部门信息
     * @param dept
     */
    @Update("update dept set name = #{name} where id = #{id}")
    void update(Dept dept);
}
