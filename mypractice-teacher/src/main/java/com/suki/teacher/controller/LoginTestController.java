package com.suki.teacher.controller;


import com.suki.common.Result;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class LoginTestController {
    @PostMapping("/login")
    public Result login(){
        Map<String,Object> map =new HashMap<>();
        map.put("token", "admin");
        return Result.ok().setData(map);
    }

    @GetMapping("/info")
    public Result info(){
        Map<String,Object> map =new HashMap<>();
        map.put("name", "admin");
        map.put("roles", "[admin,admin]");
        map.put("avatar", "https://i.loli.net/2021/11/17/pGCAIZO4TQ8NwLK.png");
        return Result.ok().setData(map);
    }
    @PostMapping("/logout")
    public Result logou(){
        return Result.ok();
    }

}
