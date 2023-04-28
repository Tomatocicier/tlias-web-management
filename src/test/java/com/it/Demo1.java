package com.it;

import com.it.controller.DeptController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationContextFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

@SpringBootTest
public class Demo1 {
    @Autowired
    private ApplicationContext applicationContext;//IOC容器对象
    @Test
    public void testGetBean(){
        //根据Bean的名城获取
        DeptController deptController = (DeptController) applicationContext.getBean("deptController");
        System.out.println(deptController);
        //根据Bean的类获取
        DeptController bean = applicationContext.getBean(DeptController.class);
        System.out.println(bean);
    }
}
