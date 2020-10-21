package com.example.user.service;

import com.example.user.entity.User;

import java.util.List;

/**
 * @ClassName: UserService
 * @Description: 用户服务
 * @Author: yongchen
 * @Date: 2020/10/21 15:48
 **/
public interface UserService {

    /**
     * @Author: yongchen
     * @Description: 获取全部用户信息
     * @Date: 15:51 2020/10/21
     * @Param: []
     * @return: java.util.List<com.example.user.entity.User>
     **/
    List<User> userList();
}
