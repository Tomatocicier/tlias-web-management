package com.it.service;

import com.it.pojo.Dept;

import java.util.List;

public interface DeptService {
    /**
     * 查询部门全部数据
     * @return
     */
    List<Dept> list();

    /**
     * 根据删除部门
     */
    void delete(Integer id);


    /**
     * 添加部门
     * @param dept
     */
    void add(Dept dept);

    /**
     * 修改部门信息
     * @param dept
     */
    void update(Dept dept);
}
