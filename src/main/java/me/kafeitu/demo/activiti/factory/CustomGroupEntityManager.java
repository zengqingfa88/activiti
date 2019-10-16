package me.kafeitu.demo.activiti.factory;


import cn.hutool.core.collection.CollUtil;
import me.kafeitu.demo.activiti.user.entity.SysRole;
import me.kafeitu.demo.activiti.user.entity.SysUser;
import me.kafeitu.demo.activiti.user.mapper.RoleRepository;
import me.kafeitu.demo.activiti.user.mapper.SysRoleMapper;
import me.kafeitu.demo.activiti.util.ActivitiUserUtils;
import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.User;
import org.activiti.engine.impl.GroupQueryImpl;
import org.activiti.engine.impl.Page;
import org.activiti.engine.impl.persistence.entity.GroupEntity;
import org.activiti.engine.impl.persistence.entity.GroupEntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author zengqingfa
 * @date 2019/10/14 15:11
 * @description
 * @email zengqingfa_java@163.com
 */
@Service
public class CustomGroupEntityManager extends GroupEntityManager {

    @Autowired
    private RoleRepository roleRepository;


    @Override
    public List<Group> findGroupsByUser( String userId) {
        if (userId == null)
            return null;
        List<SysRole> sysRoleList = roleRepository.getGroupsByUserName(new Long(userId));
        List<Group> groupList = new ArrayList<>();
        GroupEntity g;
        if(CollUtil.isNotEmpty(sysRoleList)){
            for (SysRole role : sysRoleList) {
                g = new GroupEntity();
                g.setRevision(1);
                g.setType("assignment");
                g.setId(role.getRoleId().toString());
                g.setName(role.getRoleName());
                groupList.add(g);
            }
        }
        return groupList;
    }

    @Override
    public List<Group> findGroupByQueryCriteria(GroupQueryImpl query, Page page) {
        List<SysRole> sysRoleList = roleRepository.getGroupsByUserName(new Long(query.getUserId()));
        List<Group> groupList = new ArrayList<>();
        for (SysRole role : sysRoleList) {
            GroupEntity g = new GroupEntity();
            g.setRevision(1);
            g.setType("assignment");
            g.setId(role.getRoleId().toString());
            g.setName(role.getRoleName());
            groupList.add(g);
        }
        return groupList;
    }

    @Override
    public long findGroupCountByQueryCriteria(GroupQueryImpl query) {
        throw new RuntimeException("not implement method.");
    }

    @Override
    public List<Group> findGroupsByNativeQuery(Map<String, Object> parameterMap, int firstResult, int maxResults) {
        throw new RuntimeException("not implement method.");
    }

    @Override
    public long findGroupCountByNativeQuery(Map<String, Object> parameterMap) {
        throw new RuntimeException("not implement method.");
    }

}
