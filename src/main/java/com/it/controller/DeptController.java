package com.it.controller;

import com.it.anno.Log;
import com.it.pojo.Dept;
import com.it.pojo.Result;
import com.it.service.DeptService;
import com.it.service.impl.DeptServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/depts")
public class DeptController {
    //private static Logger log = LoggerFactory.getLogger(DeptController.class);
    //@RequestMapping(value = "/depts",method = RequestMethod.GET)
    @Autowired
    private DeptServiceImpl deptService;

    /**
     * 查询部门数据
     * @return
     */
    @GetMapping
    public Result list(){
        log.info("查询全部部门");
       List<Dept> deptList =  deptService.list();
        return Result.success(deptList);
    }

    /**
     * 删除部门
     */
    @Log
    @DeleteMapping({"/{id}"})
    public Result delete(@PathVariable Integer id){
        log.info("根据ID删除部门:{}",id);
        //调用Service删除部门
        deptService.delete(id);
        return Result.success();
    }

    /**
     * 新增部门
     * @return
     */
    @Log
    @PostMapping
    public Result insert(@RequestBody Dept dept){
        log.info("新增部门:{}",dept);
        //调用Service方法新增部门
        deptService.add(dept);
        return Result.success();

    }
    @PutMapping
    public Result update(@RequestBody Dept dept){
        log.info("修改信息:{}",dept);
        deptService.update(dept);
        return Result.success(dept);

    }

}
