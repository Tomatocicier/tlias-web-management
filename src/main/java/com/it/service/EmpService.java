package com.it.service;

import com.it.pojo.Emp;
import com.it.pojo.PageBean;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

public interface EmpService {
    //分页查询员工信息
    PageBean page(Integer page, Integer pageSize,String name, Short gender, LocalDate begin, LocalDate end);



    //批量删除
    void delete(List<Integer> ids);

    //新增员工
    void save(Emp emp);

    /**
     * 根据Id查询员工并
     * @param id
     * @return
     */
    Emp getById(Integer id);

    /**
     * 更新员工
     * @param emp
     */
    void update(Emp emp);

    /**
     * 登录
     * @param emp
     */
    Emp login(Emp emp);
}
