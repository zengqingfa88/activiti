package me.kafeitu.demo.activiti.factory;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import me.kafeitu.demo.activiti.user.entity.SysRole;
import me.kafeitu.demo.activiti.user.entity.SysUser;
import me.kafeitu.demo.activiti.user.mapper.RoleRepository;
import me.kafeitu.demo.activiti.user.mapper.UserRepository;
import me.kafeitu.demo.activiti.util.ActivitiUserUtils;
import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.User;
import org.activiti.engine.impl.Page;
import org.activiti.engine.impl.UserQueryImpl;
import org.activiti.engine.impl.persistence.entity.IdentityInfoEntity;
import org.activiti.engine.impl.persistence.entity.UserEntity;
import org.activiti.engine.impl.persistence.entity.UserEntityManager;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author zengqingfa
 * @date 2019/10/14 15:11
 * @description
 * @email zengqingfa_java@163.com
 */
@Component
public class CustomUserEntityManager extends UserEntityManager {


    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;


    @Override
    public List<User> findUserByQueryCriteria(UserQueryImpl query, Page page) {
        SysUser user = userRepository.getOne(new Long(String.valueOf(query.getId())));
        List<User> list = new ArrayList<>();
        list.add(ActivitiUserUtils.toActivitiUser(user));
        return list;
    }


    @Override
    public UserEntity findUserById(final String userId) {
        if (StringUtils.isBlank(userId)) {
            return null;
        }
        SysUser sysUser = userRepository.findOne(new Long(userId));//这是我们的dao方法查询回来的方法，是自己定义的user
        UserEntity userEntity = null;
        if (!BeanUtil.isEmpty(sysUser)) {
            userEntity = ActivitiUserUtils.toActivitiUser(sysUser);//将自定义的user转化为activiti的类
        }
        return userEntity;//返回的是activiti的实体类
    }

    @Override
    public List<Group> findGroupsByUser(String userId) {
        if (StringUtils.isBlank(userId)) {
            return null;
        }
        //根据用户id查询用户具有的角色
        List<SysRole> groupIds = roleRepository.getGroupsByUserName(new Long(userId));
        List<Group> gs = null;
        if (!CollUtil.isEmpty(groupIds)) {
            gs = ActivitiUserUtils.toActivitiGroups(groupIds);
        }
        return gs;
    }




    @Override
    public long findUserCountByQueryCriteria(UserQueryImpl query) {
        return super.findUserCountByQueryCriteria(query);
    }

    @Override
    public IdentityInfoEntity findUserInfoByUserIdAndKey(String userId, String key) {
        return super.findUserInfoByUserIdAndKey(userId, key);
    }

    @Override
    public List<String> findUserInfoKeysByUserIdAndType(String userId, String type) {
        return super.findUserInfoKeysByUserIdAndType(userId, type);
    }

    @Override
    public List<org.activiti.engine.identity.User> findPotentialStarterUsers(String proceDefId) {
        return super.findPotentialStarterUsers(proceDefId);
    }

    @Override
    public List<org.activiti.engine.identity.User> findUsersByNativeQuery(Map<String, Object> parameterMap,
                                                                          int firstResult, int maxResults) {
        return super.findUsersByNativeQuery(parameterMap, firstResult, maxResults);
    }

    @Override
    public long findUserCountByNativeQuery(Map<String, Object> parameterMap) {
        return super.findUserCountByNativeQuery(parameterMap);
    }

    //校验密码
    @Override
    public Boolean checkPassword(String userId, String password) {
        SysUser sysUser = userRepository.getOne(new Long(userId));
        return sysUser.getPassword().equals(password);
    }
}