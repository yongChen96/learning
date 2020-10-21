package com.example.user.controller;

import com.example.user.entity.User;
import com.example.user.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName: UserController
 * @Description: 用户信息Controller
 * @Author: yongchen
 * @Date: 2020/10/21 15:42
 **/
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    /**
     * @Author: yongchen
     * @Description: 获取用户信息
     * @Date: 16:12 2020/10/21
     * @Param: []
     * @return: java.util.List<com.example.user.entity.User>
     **/
    @GetMapping("getUserList")
    public List<User> getUserList(){
        return userService.userList();
    }

}
