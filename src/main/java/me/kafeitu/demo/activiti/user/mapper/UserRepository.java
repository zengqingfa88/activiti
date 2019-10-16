package me.kafeitu.demo.activiti.user.mapper;

import me.kafeitu.demo.activiti.user.entity.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 * @author zengqingfa
 * @date 2019/10/15 9:05
 * @description
 * @email zengqingfa_java@163.com
 */
@Repository
public interface UserRepository extends JpaRepository<SysUser,Long> {

    @Query(value = "select  * from sys_user where login_name= ?1",nativeQuery = true)
    SysUser findByLoginName(String loginName);
}
