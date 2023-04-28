package com.it.controller;

import com.it.pojo.Emp;
import com.it.pojo.Result;
import com.it.service.EmpService;
import com.it.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
public class LoginController {
    @Autowired
    private EmpService empService;
    @PostMapping("/login")
    public Result login(@RequestBody Emp emp){
        log.info("员工登录:{}",emp);
        Emp e = empService.login(emp);

        //如果登录成功生成令牌并下发令牌
        if (e != null){
            Map<String, Object> claims = new HashMap<>();
            claims.put("id",e.getId());
            claims.put("password",e.getPassword());
            claims.put("username",e.getUsername());

            String jwt = JwtUtils.generateJwt(claims);//包含的当前登录的员工信息
            return Result.success(jwt);
        }
        return Result.error("登录失败");
    }

}
