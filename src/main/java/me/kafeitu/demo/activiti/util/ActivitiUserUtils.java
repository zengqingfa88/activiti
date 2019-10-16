package me.kafeitu.demo.activiti.util;

import me.kafeitu.demo.activiti.user.entity.SysRole;
import me.kafeitu.demo.activiti.user.entity.SysUser;
import org.activiti.engine.identity.Group;
import org.activiti.engine.impl.persistence.entity.GroupEntity;
import org.activiti.engine.impl.persistence.entity.UserEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zengqingfa
 * @date 2019/10/14 15:23
 * @description
 * @email zengqingfa_java@163.com
 */
public class ActivitiUserUtils {

    public static UserEntity toActivitiUser(SysUser sysUser) {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(sysUser.getUserId().toString());
        userEntity.setPassword(sysUser.getPassword());
        userEntity.setEmail(sysUser.getEmail());
        userEntity.setRevision(1);
        return userEntity;
    }


    public static List<Group> toActivitiGroups(List<SysRole> sysRoles) {
        List<Group> groups = new ArrayList<Group>();
        for (SysRole sysRole : sysRoles) {
            GroupEntity groupEntity = toActivitiGroup(sysRole);
            groups.add(groupEntity);
        }
        return groups;
    }

    public static GroupEntity toActivitiGroup(SysRole sysRole) {
        GroupEntity groupEntity = new GroupEntity();
        groupEntity.setRevision(1);
        groupEntity.setType("assignment");
        groupEntity.setId(sysRole.getRoleId().toString());
        groupEntity.setName(sysRole.getRoleName());
        return groupEntity;
    }
}