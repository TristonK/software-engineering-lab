package com.example.springweb.service;

import com.example.springweb.dao.Appcheck;
import com.example.springweb.mapper.AppcheckMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class AppcheckService {
    @Resource
    private AppcheckMapper appcheckMapper;

    public List<Appcheck> getAppList(){
        List<Appcheck> list = appcheckMapper.findAllApp();
        return list;
    }

    public void InsertApp(Map<String, String> params){
        ObjectMapper objectMapper = new ObjectMapper();
        Appcheck appcheck = objectMapper.convertValue(params, Appcheck.class);
        appcheckMapper.insertApp(appcheck);
    }

    public Appcheck getOneApp(String pid){
        Appcheck result = appcheckMapper.getOneApp(pid);
        System.out.println("getOneApp:"+result);
        if (result==null)
        {
            result=new Appcheck();
        }
        System.out.println(result.toString());
        return result;
    }

    public List<Appcheck> getAppByUsr(String uid){
        List<Appcheck> list = appcheckMapper.getAppByUsr(uid);
        return list;
    }

    public void UpdateByID(Map<String, String> params){
        String pid = params.get("pid");
        Appcheck temp = appcheckMapper.getOneApp(pid);
        if(params.get("pname")!=null)
            temp.setPname(params.get("pname"));
        if(params.get("pcate")!=null)
            temp.setPcate(params.get("pcate"));
        if(params.get("pcateinfo")!=null)
            temp.setPcateinfo(params.get("pcateinfo"));
        if(params.get("psecurity")!=null)
            temp.setPsecurity(params.get("psecurity"));
        if(params.get("psecuinfo")!=null)
            temp.setPsecuinfo(params.get("psecuinfo"));
        if(params.get("padvanced")!=null)
            temp.setPadvanced(params.get("padvanced"));
        if(params.get("pcheckinfo")!=null)
            temp.setPcheckinfo(params.get("pcheckinfo"));
        if(params.get("uid")!=null)
            temp.setUid(params.get("uid"));
        if(params.get("status")!=null)
            temp.setStatus(params.get("status"));
        if(params.get("rate")!=null)
            temp.setRate(params.get("rate"));
        appcheckMapper.updateAppById(temp);
    }

    public void DeleteByID(String pid){
        appcheckMapper.deleteApp(pid);;
        System.out.println("AfterDelete:"+appcheckMapper.getOneApp(pid));
    }
}
