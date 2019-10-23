package me.kafeitu.demo.activiti.user.mapper;

import me.kafeitu.demo.activiti.user.entity.SysRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author zengqingfa
 * @date 2019/10/15 9:06
 * @description
 * @email zengqingfa_java@163.com
 */
@Repository
public interface RoleRepository extends JpaRepository<SysRole, Long> {

    @Query(value = "select   r.*  from  sys_user_role ur    right join sys_role r  on  r.role_id=ur.role_id   where ur.user_id =?1", nativeQuery = true)
    List<SysRole> getGroupsByUserName(Long userId);

    @Query(value = "select * from sys_role", nativeQuery = true)
    List<SysRole> getGroupsByUserName2();

}
