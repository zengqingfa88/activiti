package me.kafeitu.demo.activiti.factory;

import org.activiti.engine.impl.interceptor.Session;
import org.activiti.engine.impl.interceptor.SessionFactory;
import org.activiti.engine.impl.persistence.entity.GroupEntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @author zengqingfa
 * @date 2019/10/14 15:10
 * @description
 * @email zengqingfa_java@163.com
 */

@Service
public class CustomGroupEntityManagerFactory implements SessionFactory {

    //自定义组管理
    private CustomGroupEntityManager customGroupEntityManager;

    public Class<?> getSessionType() {
        // 返回原始的GroupManager类型
        return GroupEntityManager.class;
    }

    public Session openSession() {
        // 返回自定义的GroupManager实例
        return customGroupEntityManager;
    }

    @Autowired
    public void setCustomGroupEntityManager(CustomGroupEntityManager customGroupEntityManager) {
        this.customGroupEntityManager = customGroupEntityManager;
    }
}
