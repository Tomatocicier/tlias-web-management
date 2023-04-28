package com.it.aop;

import com.alibaba.fastjson.JSONObject;
import com.it.mapper.OperateLogMapper;
import com.it.pojo.OperateLog;
import com.it.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Map;

@Slf4j
@Component
@Aspect//切面类
public class LogAspect {
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private OperateLogMapper operateLogMapper;
    @Around("@annotation(com.it.anno.Log)")
    public Object recordLog(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        //操作人id
        //解析JWT令牌
        String jwt = request.getHeader("token");
        Map<String, Object> claims = JwtUtils.parseJWT(jwt);
        Integer operateUser = (Integer) claims.get("id");

        //操作时间
        LocalDateTime operateTime = LocalDateTime.now();

        //操作类名
        String className = proceedingJoinPoint.getClass().getName();

        //操作方法名
        String methodName = proceedingJoinPoint.getSignature().getName();


        //操作方法参数
        Object[] args = proceedingJoinPoint.getArgs();
        String methodParams = Arrays.toString(args);

        long begin = System.currentTimeMillis();
        //调用原始目标方法运行
        Object result = proceedingJoinPoint.proceed();
        long end = System.currentTimeMillis();
        //获取返回值
        String returnValue = JSONObject.toJSONString(result);
        //方法运行耗时
        Long costTime = end - begin;

        //记录日志
        OperateLog operateLog = new OperateLog(null,operateUser,operateTime,className,methodName,methodParams,returnValue,costTime);
        operateLogMapper.insert(operateLog);

        log.info("AOP操作日志:{}",operateLog);


        return result;


    }

}
