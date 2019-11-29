package com.example.springweb;

import com.example.springweb.dao.HelloUser;
import com.example.springweb.service.HelloService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HelloServiceTest {
    @Autowired
    HelloService helloService;

    @Test
    public void testUsers_1() throws Exception {
        //检测初始getUserList是否正常
        List<HelloUser> users = helloService.getUserList();
        assertFalse("User not null", users == null);
        assertNotNull(users);
        assertEquals(users.size(), 2);
        assertEquals(users.get(0).getName(), "鸡肉工厂");
        //检测InsertUser与GetOne在输入正常的情况下是否正常
        Map<String, String> newusr = new HashMap<String, String>();
        newusr.put("id","003");
        newusr.put("name","stupid factory");
        newusr.put("password","abcde");
        helloService.InsertUser(newusr);
        HelloUser newadd = helloService.getOne("003");
        assertEquals(newadd.getName(),"stupid factory");
        assertEquals(newadd.getPassword(),"abcde");
        //检测UpdateByID与getUsrList是否正常
        newusr.replace("password","edcba");
        helloService.UpdateByID(newusr);
        List<HelloUser> usrs2 =  helloService.getUserList();
        assertEquals(usrs2.size(),3);
        assertEquals(usrs2.get(2).getPassword(),"edcba");
        //检测DeleteByID函数与getOne在ID不存在的情况下是否返回空值
        helloService.DeleteByID("003");
        HelloUser deletedUsr = helloService.getOne("003");
        assertEquals(deletedUsr.getName(),null);
        //检测删除不存在的ID是否生效
        List<HelloUser> Deleteusrs = helloService.getUserList();
        assertEquals(Deleteusrs.size(),2);
        helloService.DeleteByID("010");
        List<HelloUser> Deleteusrs2 = helloService.getUserList();
        assertEquals(Deleteusrs2.size(),2);
    }

    // 检测插入相同ID用户时是否抛出异常
    @Test (expected = Exception.class)
    public void testInsertException() throws Exception{
        Map<String,String> params=new HashMap<>();
        params.put("id","001");
        params.put("name","interestingFactory");
        params.put("password","ispassword");
        helloService.InsertUser(params);
        fail("插入相同userID的用户没有抛出异常");
    }

    //检测更新不存在用户时是否抛出异常
    @Test (expected = Exception.class)
    public void testUpdateException() throws Exception{
        Map<String,String> params=new HashMap<>();
        params.put("id","005");
        params.put("name","interestingFactory");
        params.put("password","ispassword");
        helloService.UpdateByID(params);
        fail("更新不存在的用户没有抛出异常");
    }
}
