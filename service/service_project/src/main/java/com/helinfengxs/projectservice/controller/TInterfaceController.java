package com.helinfengxs.projectservice.controller;


import com.helinfengxs.commonutils.R;
import com.helinfengxs.projectservice.entity.TInterface;
import com.helinfengxs.projectservice.service.TInterfaceService;
import com.helinfengxs.projectservice.vo.InterfacePageQuery;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 接口信息表 前端控制器
 * </p>
 *
 * @author helinfeng
 * @since 2021-05-16
 */
@RestController
@RequestMapping("/projectservice/interface")
@CrossOrigin
@Api(description = "接口信息管理")
public class TInterfaceController {
    @Autowired
    TInterfaceService tInterfaceService;


    /**
     * 条件组合分页查询
     * @param current 当前页
     * @param limit 当前页展示数
     * @param interfacePageQuery 条件组合查询对象
     * @return 返回查询结果
     */
    @ApiOperation("条件组合分页查询")
    @PostMapping("pageInterface/{current}/{limit}")
    public R pageInterface(
            @PathVariable long current,
            @PathVariable long limit,
            @RequestBody(required = false)  InterfacePageQuery interfacePageQuery ){
        System.out.println(interfacePageQuery);
        HashMap<String,Object> hashMap =  tInterfaceService.pageInterface(current,limit,interfacePageQuery);

        return R.ok().data(hashMap);
    }


    /**
     * 根据接口Id查询接口信息
     * @param id 接口ID
     * @return 返回接口信息
     */
    @GetMapping("findInterface/{id}")
    @ApiOperation("根据接口Id查询接口信息")
    public R findInterfaceById(
            @PathVariable String id){
        TInterface tInterface = tInterfaceService.findInterfaceById(id);

        return R.ok().data("item",tInterface);
    }

    /**
     * 根据接口id删除接口信息
     * @param id 接口id
     * @return 删除成功信息
     */
    @DeleteMapping("deleteInterface/{id}")
    @ApiOperation("根据接口id删除接口信息")
    public R deleteInterById(
            @PathVariable String id){
        tInterfaceService.deleteInterById(id);
        return R.ok().message("删除接口信息成功");
    }

    /**
     * 添加接口信息
     * @param tInterface 接口信息实体类
     * @return 返回添加成功消息
     */
    @PostMapping("addInterface")
    @ApiOperation("添加接口信息")
    public R addInterface(@RequestBody TInterface tInterface){
        tInterfaceService.addInterface(tInterface);
        return R.ok().message("添加接口成功");
    }

    /**
     * 更新项目信息
     * @param tInterface 接口信息实体类
     * @return 返回更新成功消息
     */
    @PostMapping("updateInterfaceInfo")
    @ApiOperation("更新接口信息")
    public R updateInterfaceInfo(@RequestBody TInterface tInterface){
        tInterfaceService.updateInterfaceInfo(tInterface);
        return R.ok().message("更新接口信息成功");
    }

    /**
     * 批量删除接口信息
     * @param ids 接口id列表
     * @return 返回批量删除成功消息
     */
    @DeleteMapping("deletePartInterface")
    @ApiOperation("批量删除接口信息")
    public R deletePartInterface(@RequestBody List<String> ids){
        tInterfaceService.deletePartInterface(ids);
        return R.ok().message("批量删除接口成功");
    }
}

