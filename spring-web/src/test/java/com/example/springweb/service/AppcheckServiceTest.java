package com.example.springweb.service;

import com.example.springweb.dao.Appcheck;
import com.example.springweb.service.AppcheckService;
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
public class AppcheckServiceTest {
    @Autowired
    AppcheckService appcheckService;

    @Test
    public void testApps() throws Exception{
        //检测初始getAPPList是否正常
        List<Appcheck> apps1 = appcheckService.getAppList();
        assertFalse("app not null", apps1 == null);
        assertNotNull(apps1);
        assertEquals(apps1.size(), 4);
        assertEquals(apps1.get(1).getPname(), "打工APP");
        //检测初始getAppByUsr是否正常
        List<Appcheck> apps001 = appcheckService.getAppByUsr("001");
        assertFalse("app not null", apps001 == null);
        assertNotNull(apps001);
        assertEquals(apps001.size(), 3);
        assertEquals(apps001.get(2).getPname(), "工业鸡切割APP");
        List<Appcheck> apps005 = appcheckService.getAppByUsr("005");
        assertEquals(apps005.size(),0);
        //检测GetOneApp在检测不存在的提交材料的时候是否返回空
        Appcheck unexistedApp = appcheckService.getOneApp("888888");
        assertEquals(unexistedApp.getPname(),null);
        //检测InsertUser与GetOne在输入正常的情况下是否正常
        Map<String, String> newapp = new HashMap<String, String>();
        newapp.put("pid","888888");
        newapp.put("uid","005");
        newapp.put("pname","LOLapp");
        newapp.put("pcate","生产类");
        newapp.put("status","待审核");
        appcheckService.InsertApp(newapp);
        Appcheck testNew = appcheckService.getOneApp("888888");
        assertEquals(testNew.getPname(),"LOLapp");
        assertEquals(testNew.getPcateinfo(),null);
        //检测UpdateByID与getAppByUsr是否正常
        newapp.put("pcateinfo","nice cate");
        newapp.replace("pname","DotaAPP");
        appcheckService.UpdateByID(newapp);
        apps005 = appcheckService.getAppByUsr("005");
        assertEquals(apps005.size(),1);
        assertEquals(apps005.get(0).getPcateinfo(),"nice cate");
        assertEquals(apps005.get(0).getPname(),"DotaAPP");
        //检测DeleteByID函数
        appcheckService.DeleteByID("888888");
        List<Appcheck> appList2 = appcheckService.getAppList();
        assertEquals(appList2.size(), 4);
        //检测删除不存在的ID是否生效
        appcheckService.DeleteByID("66666");
        List<Appcheck> appList3 = appcheckService.getAppList();
        assertEquals(appList3.size(), 4);
    }

    @Test (expected = Exception.class)
    public void testInsertException() throws Exception{
        Appcheck test = appcheckService.getOneApp("11254");
        assertEquals(test.getPname(),"量子波动鸡增长APP");
        Map<String,String> app = new HashMap<>();
        app.put("pid","11254");
        app.put("pname","量子鸡APP");
        app.put("pcate","bb类");
        app.put("pcateinfo","这是一个分类说明");
        app.put("psecurity","I");
        app.put("psecuinfo","it is a secu info");
        app.put("padvanced","using liangzi is advanced");
        app.put("pcheckinfo","检查好了");
        app.put("uid","001");
        app.put("status","审核中");
        app.put("rate","66");
        appcheckService.InsertApp(app);
        fail("插入相同ID的APP材料没有抛出异常");
    }

    @Test (expected = Exception.class)
    public void testUpdateException() {
        Map<String,String> app = new HashMap<>();
        app.put("pid","77777");
        app.put("pname","不存在的APP");
        appcheckService.UpdateByID(app);
        fail("更新不存在的材料没有抛出异常");
    }

}