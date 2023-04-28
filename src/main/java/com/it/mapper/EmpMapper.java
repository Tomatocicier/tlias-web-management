package com.it.mapper;

import com.it.pojo.Emp;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.omg.PortableInterceptor.INACTIVE;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface EmpMapper {
    /**
     * 获取总记录数
     */
    //@Select("select count(*) from emp")
   //public Long count();


    /**
     * 分页查询获取列表数据
     * @return
     */
    //@Select("select * from emp limit #{start},#{pageSize}")
    //List<Emp> page(Integer start,Integer pageSize);


    /**
     *员工信息查询
     */
    //@Select("select * from emp where like ")
    public List<Emp> list(String name, Short gender, LocalDate begin, LocalDate end);

    /**
     * 批量删除
     */

    void delete(List<Integer> ids);

    /**
     * 新增员工
     * @param emp
     */
    @Insert("insert into emp(username, name, gender, image, job, entrydate, dept_id, create_time, update_time) " +
            "values (#{username},#{name},#{gender},#{image},#{job},#{entrydate},#{deptId},#{createTime},#{updateTime})")
    void insert(Emp emp);

    /**
     * 根据ID查询员工
     * @param id
     * @return
     */
    @Select("select * from emp where id = #{id}")
    Emp getById(Integer id);


    /**
     * 更新员工操作
     * @param emp
     */
    void update(Emp emp);

    /**
     * 查询员工账号密码
     * @param emp
     * @return
     */
    @Select("select id,username,password from emp " +
            "where username = #{username} and password = #{password}")
    Emp getByUsernameAndPassWord(Emp emp);

    /**
     * 删除部门的同时，删除部门下所有员工
     */
    @Delete("delete from emp where dept_id = #{deptId}")
    void deleteByDeptId(Integer deptId);
}
