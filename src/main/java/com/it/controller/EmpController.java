package com.it.controller;

import com.it.anno.Log;
import com.it.pojo.Emp;
import com.it.pojo.PageBean;
import com.it.pojo.Result;
import com.it.service.EmpService;
import com.it.service.impl.EmpServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/emps")
public class EmpController {
    @Autowired
    private EmpService empService;

    @GetMapping
    public Result page(@RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10")Integer pageSize,
                       String name, Short gender,
                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end){
        log.info("分页查询,参数:{},{},{},{},{},{}",page,pageSize,name,gender,begin,end);
        PageBean pageBean = empService.page(page,pageSize,name,gender,begin,end);
        return Result.success(pageBean);
    }

    /**
     * 删除员工
     */
    @Log
    @DeleteMapping("/{ids}")
    public Result delete(@PathVariable List<Integer> ids){
        log.info("批量删除id:{}",ids);
        empService.delete(ids);
    return Result.success();
    }

    /**
     * 新增员工
     * @return
     */
    @Log
    @PostMapping
    public Result save(@RequestBody Emp emp){
        log.info("新增员工,emp:{}",emp);
        empService.save(emp);
        return  Result.success();
    }

    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id){
        log.info("根据id查询员工信息,id:{}",id);
        Emp emp = empService.getById(id);
        return Result.success(emp);
    }

    @Log
    @PutMapping
    public Result update(@RequestBody Emp emp){
        log.info("更新信息:{}",emp);
        empService.update(emp);
        return Result.success();

    }


}
