package com.helinfengxs.projectservice.controller;


import com.helinfengxs.commonutils.R;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/user/")
@CrossOrigin
@Api(description = "项目管理")
public class PlateformLoginController {

    @PostMapping("login")
    public R login(){
        return R.ok().data("token","admin");
    }
    @GetMapping("info")
    public R getInfo(){
        HashMap<String,Object> hashMap = new HashMap<>();
        hashMap.put("roles","admin");
        hashMap.put("name","admin");
        hashMap.put("avatar","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        return R.ok().data(hashMap);
    }
    @PostMapping("logout")
    public R logout(){
        return R.ok().message("退出成功");
    }
}
