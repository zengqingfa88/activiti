package me.kafeitu.demo.activiti.web.oa.leave;

import me.kafeitu.demo.activiti.user.entity.SysUser;
import me.kafeitu.demo.activiti.user.mapper.UserRepository;
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
@RequestMapping("/user")
public class CustomUserController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping("/getUserList")
    public Page<SysUser> getUserListByPage(int pageNo, int pageSize) {
        if (pageNo == 0) pageNo = 1;
        if (pageSize == 0) pageSize = 2;
        Page<SysUser> users = userRepository.findAll(new PageRequest(pageNo-1, pageSize));
        return users;
    }
}
