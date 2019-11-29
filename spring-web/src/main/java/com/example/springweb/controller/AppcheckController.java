package com.example.springweb.controller;

import com.example.springweb.dao.Appcheck;
import com.example.springweb.service.AppcheckService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class AppcheckController {
    @Autowired
    AppcheckService appcheckService;
    public final static Logger logger = LoggerFactory.getLogger(AppcheckController.class);

    @RequestMapping(value = "addApp", method = RequestMethod.GET)
    public String ApptoAdd(){
        logger.info("createNewAppForm");
        return "addApp";
    }
    @RequestMapping(value = "addApp", method = RequestMethod.POST)
    public String addApp(Appcheck app, Model model, HttpSession session){
        String pid = app.getPid();
        Appcheck apptest = appcheckService.getOneApp(pid);
        System.out.println("get pid is "+pid);
        if(apptest.getPname()!=null){
            model.addAttribute("addAppInfo","此编号已被占用，请选择其他编号");
            return "addApp";
        }
        String uid=app.getUid();
        String pname=app.getPname();
        String pcate=app.getPcate();
        String pcateinfo=app.getPcateinfo();
        String psecurity=app.getPsecurity();
        String psecuinfo=app.getPsecuinfo();
        String padvanced=app.getPadvanced();
        String pcheckinfo=app.getPcheckinfo();
        Map<String, String> newapp = new HashMap<String, String>();
        newapp.put("pid",pid);
        newapp.put("pname",pname);
        newapp.put("pcate",pcate);
        newapp.put("pcateinfo",pcateinfo);
        newapp.put("psecurity",psecurity);
        newapp.put("psecuinfo",psecuinfo);
        newapp.put("padvanced",padvanced);
        newapp.put("pcheckinfo",pcheckinfo);
        newapp.put("uid",uid);
        newapp.put("status","待审核");
        newapp.put("rate","0");
        newapp.put("comment",null);
        appcheckService.InsertApp(newapp);
        return "redirect:dashboard";
    }

    @RequestMapping("/dashboard")
    public String dashboard(Appcheck app, Model model,HttpSession session){
        String usrId = (String) session.getAttribute("usrID");
        if(usrId==null)
            return "redirect:login";
        List<Appcheck> list = appcheckService.getAppByUsr(usrId);
        model.addAttribute("list", list);
        return "dashboard";
    }

    @RequestMapping("/viewM/{pid}")
    public String viewma(@PathVariable("pid") Integer pid,Model model,HttpSession session){
        String id = toString().valueOf(pid);
        Appcheck app = appcheckService.getOneApp(id);
        session.setAttribute("appToShow",app);
        return "redirect:../material";
    }

    @RequestMapping("/viewA/{pid}")
    public String viewAns(@PathVariable("pid") Integer pid,Model model,HttpSession session){
        String id = toString().valueOf(pid);
        Appcheck app = appcheckService.getOneApp(id);
        session.setAttribute("appToShow",app);
        return "redirect:../checkResult";
    }

    @RequestMapping("/deleteM/{pid}")
    public String deleteM(@PathVariable("pid") Integer pid,Model model,HttpSession session){
        String id = toString().valueOf(pid);
        appcheckService.DeleteByID(id);
        return "redirect:../dashboard";
    }

    @RequestMapping(value="/material",method = RequestMethod.GET)
    public String getMaterial(){
        return "material";
    }

    @RequestMapping(value="/checkResult",method = RequestMethod.GET)
    public String getResult(){
        return "checkResult";
    }
}
