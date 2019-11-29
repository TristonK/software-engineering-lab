package com.example.springweb.dao;

import java.io.Serializable;

public class Appcheck implements Serializable{
    private String pid;
    private String pname;
    private String pcate;
    private String pcateinfo;
    private String psecurity;
    private String psecuinfo;
    private String padvanced;
    private String pcheckinfo;
    private String uid;
    private String status;
    private String rate;
    private String comment;


    public Appcheck(){
        pid = null;
        pname=null;
        pcate=null;
        pcateinfo=null;
        psecurity=null;
        psecuinfo=null;
        padvanced=null;
        pcheckinfo=null;
        uid=null;
        status=null;
        rate=null;
        comment=null;
    }
    public Appcheck(String pid, String pname, String pcate, String pcateinfo, String psecurity, String psecuinfo, String padvanced, String pcheckinfo, String uid, String status, String rate,String comment){
        this.pid = pid;
        this.pname=pname;
        this.pcate=pcate;
        this.pcateinfo=pcateinfo;
        this.psecurity=psecurity;
        this.psecuinfo=psecuinfo;
        this.padvanced=padvanced;
        this.pcheckinfo=pcheckinfo;
        this.uid=uid;
        this.status=status;
        this.rate=rate;
        this.comment=comment;
    }

    public String getPid(){ return pid; }

    public void setPid(String pid){ this.pid = pid; }

    public String getPname(){ return pname; }

    public void setPname(String pname){ this.pname = pname; }

    public String getPcate(){ return pcate; }

    public void setPcate(String pcate){ this.pcate = pcate; }

    public String getPcateinfo(){ return pcateinfo;}

    public void setPcateinfo(String pcateinfo){ this.pcateinfo = pcateinfo;}

    public String getPsecurity(){return psecurity;}

    public void setPsecurity(String psecurity){this.psecurity=psecurity;}

    public String getPsecuinfo() {return psecuinfo;}

    public void setPsecuinfo(String psecuinfo){this.psecuinfo=psecuinfo;}

    public String getPadvanced(){return padvanced;}

    public void setPadvanced(String padvanced){this.padvanced = padvanced;}

    public String getPcheckinfo() {return pcheckinfo;}

    public void setPcheckinfo(String pcheckinfo){this.pcheckinfo = pcheckinfo;}

    public String getUid(){ return uid;}

    public void setUid(String uid){ this.uid = uid;}

    public String getStatus(){ return status;}

    public void setStatus(String status){ this.status=status;}

    public String getRate(){return rate;}

    public void setRate(String rate){this.rate=rate;}

    public String getComment(){return comment;}

    public void setComment(String comment){this.comment=comment;}

    @Override
    public String toString() {
        return pid + "," + pname + "," + pcate + "," + pcateinfo + "," + psecurity + "," + psecuinfo + "," + padvanced + "," + pcheckinfo;
    }

}

