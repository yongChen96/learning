package com.example.minio.group;

import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @ClassName: UserController
 * @Description: //TODO
 * @Author: yongchen
 * @Date: 2020/11/12 17:35
 **/
@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping("/getUser")
    @ApiOperation(value = "获取用户名")
    public Map<String, Object> getUser(@RequestParam String userName){
        Map<String, Object> map = new HashMap<>();
        map.put("code",200);
        map.put("msg","");
        Map<String, String> user = new HashMap<>();
        user.put("id", UUID.randomUUID().toString());
        user.put("name", userName);
        user.put("age","21");
        user.put("sex","男");
        map.put("data",user);
        return map;
    }
}
