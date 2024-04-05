package com.green.Nolloo.restAPI.service;
import com.green.Nolloo.restAPI.vo.MyEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Mapper
public interface MyMapper {
    @Select("SELECT * FROM zipcode limit 1000")
    List<MyEntity> getAllEntities();

    @Select("SELECT sigungu FROM zipcode WHERE sido = #{sido} GROUP BY sigungu HAVING COUNT(*) > 1;")
    List<String> searchEntities(@RequestParam(name="sido") String sido);
}