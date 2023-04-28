package com.it.service.impl;

import com.it.mapper.DeptMapper;
import com.it.mapper.EmpMapper;
import com.it.pojo.Dept;
import com.it.pojo.Result;
import com.it.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.beans.Transient;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {
    @Autowired
    private DeptMapper deptMapper;
    @Autowired
    private EmpMapper empMapper;
    //查询部门数据
    @Override
    public List<Dept> list() {
        return deptMapper.list();
    }

    //根据id删除部门
    @Transactional(rollbackFor = Exception.class)//Spring事务管理
    @Override
    public void delete(Integer id) {
        deptMapper.deleteById(id);
        //根据部门数据，删除部门下的员工数据
        empMapper.deleteByDeptId(id);
    }


    //添加部门数据
    @Override
    public void add(Dept dept) {
        dept.setCreateTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.add(dept);
    }
    //修改部门信息
    @Override
    public void update(Dept dept) {
        deptMapper.update(dept);

    }
}
