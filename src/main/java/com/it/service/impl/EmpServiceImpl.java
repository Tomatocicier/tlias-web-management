package com.it.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.it.mapper.EmpMapper;
import com.it.pojo.Emp;
import com.it.pojo.PageBean;
import com.it.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class EmpServiceImpl implements EmpService {
    @Autowired
    private EmpMapper empMapper;
    //查询方法
//    @Override
//    public PageBean page(Integer page, Integer pageSize) {
//        //获取总记录数
//        Long count = empMapper.count();
//        //获取分页查询结果
//        Integer start = (page - 1) * pageSize;
//        List<Emp> empList = empMapper.page(start, pageSize);
//        //封装pageBean对象
//        PageBean pageBean = new PageBean(count,empList);
//        return pageBean;
//    }



    @Override
    public PageBean page(Integer page, Integer pageSize,String name, Short gender, LocalDate begin, LocalDate end) {
//        使用pageHelper进行分页查询
        //设置分页参数
        PageHelper.startPage(page,pageSize);
        //执行查询
        List<Emp> empList = empMapper.list(name, gender, begin, end);
        Page<Emp> p = (Page<Emp>) empList;
        //封装pageBean对象
        PageBean pageBean = new PageBean(p.getTotal(),p.getResult());
        return pageBean;
    }


    //删除方法
    @Override
    public void delete(List<Integer> ids) {
        empMapper.delete(ids);
    }


    //新增员工
    @Override
    public void save(Emp emp) {
        emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.insert(emp);


    }
    //查询员工并编辑
    @Override
    public Emp getById(Integer id) {
        return empMapper.getById(id);
    }
    //编辑员工
    @Override
    public void update(Emp emp) {
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.update(emp);

    }

    @Override
    public Emp login(Emp emp) {
    return empMapper.getByUsernameAndPassWord(emp);
    }

}
