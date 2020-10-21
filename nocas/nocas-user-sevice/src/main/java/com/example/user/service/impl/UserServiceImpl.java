package com.example.user.service.impl;

import com.example.user.entity.User;
import com.example.user.service.UserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @ClassName: UserServiceImpl
 * @Description: 用户服务实现类
 * @Author: yongchen
 * @Date: 2020/10/21 15:49
 **/
@Service
public class UserServiceImpl implements UserService {

    /**
     * @Author: yongchen
     * @Description: 获取全部用户信息
     * @Date: 15:58 2020/10/21
     * @Param: []
     * @return: java.util.List<com.example.user.entity.User>
     **/
    @Override
    public List<User> userList() {
        List<User> users = new ArrayList<>();
        String[] strings = {"张山", "李四", "王五", "刘六", "赵七"};
        for (int i = 0; i < 5; i++) {
            User user = new User();
            user.setId(UUID.randomUUID().toString());
            user.setName(strings[i]);
            user.setAge(20+i);
            user.setSex("男");
            users.add(user);
        }
        return users;
    }
}
