package com.example.user.service;

import com.example.user.entity.User;

import java.util.List;

/**
 * @ClassName: UserService
 * @Description: 用户服务
 * @Author: yongchen
 * @Date: 2020/10/22 10:46
 **/
public interface UserService {
    void create(User user);

    User getUser(Long id);

    void update(User user);

    void delete(Long id);

    User getByUsername(String username);

    List<User> getUserByIds(List<Long> ids);
}
