package com.example.springweb.mapper;

import com.example.springweb.dao.Appcheck;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface AppcheckMapper {
    @Select("select * from app ")
    @Results({
            @Result(property = "pid", column = "Id"),
            @Result(property = "pname", column = "pname"),
            @Result(property = "pcate", column = "pcate"),
            @Result(property = "pcateinfo",column = "pcateinfo"),
            @Result(property = "psecurity",column = "psecurity"),
            @Result(property = "psecuinfo",column = "psecuinfo"),
            @Result(property = "padvanced",column = "padvanced"),
            @Result(property = "pcheckinfo",column = "pcheckinfo"),
            @Result(property = "uid",column = "uid"),
            @Result(property = "status",column = "status"),
            @Result(property = "rate",column = "rate")
    })
    List<Appcheck> findAllApp();

    @Insert("insert into app(Id,pname,pcate,pcateinfo,psecurity,psecuinfo,padvanced,pcheckinfo,uid,status,rate,comment) values(#{pid},#{pname},#{pcate},#{pcateinfo},#{psecurity},#{psecuinfo},#{padvanced},#{pcheckinfo},#{uid},#{status},#{rate},#{comment})")
    void insertApp(Appcheck appcheck);

    @Select("select * from app where Id=#{pid}")
    @Results({
            @Result(property = "pid", column = "Id"),
            @Result(property = "pname", column = "pname"),
            @Result(property = "pcate", column = "pcate"),
            @Result(property = "pcateinfo",column = "pcateinfo"),
            @Result(property = "psecurity",column = "psecurity"),
            @Result(property = "psecuinfo",column = "psecuinfo"),
            @Result(property = "padvanced",column = "padvanced"),
            @Result(property = "pcheckinfo",column = "pcheckinfo"),
            @Result(property = "uid",column = "uid"),
            @Result(property = "status",column = "status"),
            @Result(property = "rate",column = "rate")
    })
    Appcheck getOneApp(String pid);

    @Select("select * from app where uid=#{uid}")
    @Results({
            @Result(property = "pid", column = "Id"),
            @Result(property = "pname", column = "pname"),
            @Result(property = "pcate", column = "pcate"),
            @Result(property = "pcateinfo",column = "pcateinfo"),
            @Result(property = "psecurity",column = "psecurity"),
            @Result(property = "psecuinfo",column = "psecuinfo"),
            @Result(property = "padvanced",column = "padvanced"),
            @Result(property = "pcheckinfo",column = "pcheckinfo"),
            @Result(property = "uid",column = "uid"),
            @Result(property = "status",column = "status"),
            @Result(property = "rate",column = "rate")
    })
    List<Appcheck> getAppByUsr(String uid);

    @Update("update app set pname = #{pname},pcate = #{pcate},pcateinfo = #{pcateinfo},psecurity = #{psecurity},psecuinfo = #{psecuinfo},padvanced = #{padvanced},pcheckinfo = #{pcheckinfo} where Id = #{pid}")
    void updateAppById(Appcheck appcheck);

    @Delete("delete from app where Id=#{pid}")
    void deleteApp(String pid);
}
