package me.kafeitu.demo.activiti.user.mapper;


import me.kafeitu.demo.activiti.user.entity.SysUserRole;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;


public interface SysUserRoleMapper {
    int insert(SysUserRole record);

    int insertSelective(SysUserRole record);
}