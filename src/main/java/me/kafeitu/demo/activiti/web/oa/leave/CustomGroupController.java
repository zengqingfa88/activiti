package me.kafeitu.demo.activiti.web.oa.leave;

import me.kafeitu.demo.activiti.user.entity.SysRole;
import me.kafeitu.demo.activiti.user.mapper.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author zengqingfa
 * @date 2019/10/17 14:18
 * @description
 * @email zengqingfa_java@163.com
 */

@RestController
@RequestMapping("/group")
public class CustomGroupController {

    @Autowired
    private RoleRepository roleRepository;

    @RequestMapping("/getGroupList")
    public Page<SysRole> getGroupListByPage(int pageNo, int pageSize) {
        if (pageNo == 0) pageNo = 1;
        if (pageSize == 0) pageSize = 2;
        Page<SysRole> roles = roleRepository.findAll(new PageRequest(pageNo-1, pageSize));
        return roles;
    }


    @RequestMapping("/hello")
    public String hello() {
        return "hello world";
    }

}
