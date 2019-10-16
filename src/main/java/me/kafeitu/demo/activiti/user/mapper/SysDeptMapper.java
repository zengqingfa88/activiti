package me.kafeitu.demo.activiti.user.mapper;

import me.kafeitu.demo.activiti.user.entity.SysDept;
import org.springframework.stereotype.Repository;


public interface SysDeptMapper {
    int deleteByPrimaryKey(Long deptId);

    int insert(SysDept record);

    int insertSelective(SysDept record);

    SysDept selectByPrimaryKey(Long deptId);

    int updateByPrimaryKeySelective(SysDept record);

    int updateByPrimaryKey(SysDept record);
}