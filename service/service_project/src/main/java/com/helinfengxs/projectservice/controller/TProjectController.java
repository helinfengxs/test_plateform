package com.helinfengxs.projectservice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.helinfengxs.commonutils.R;
import com.helinfengxs.projectservice.entity.TProject;
import com.helinfengxs.projectservice.service.TProjectService;
import com.helinfengxs.projectservice.vo.ProjectList;
import com.helinfengxs.projectservice.vo.ProjectQUery;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author helinfeng
 * @since 2021-05-06
 */
@RestController
@RequestMapping("/projectservice/")
@Api(description = "项目信息管理")
@CrossOrigin
public class TProjectController {

    @Autowired
    private TProjectService tProjectService;

    /**
     * 查询所有项目信息
     * @return 返回项目列表信息
     */
    @GetMapping("findAll")
    @ApiOperation(value = "查询所有项目列表")
    public R findAll(){
        List<TProject> list  = tProjectService.findAll();

        return R.ok().data("items",list);
    }

    /**
     * 根据项目id删除项目接口
     * @param id 项目id
     * @return 返回删除是否成功
     */
    @DeleteMapping("{id}")
    @ApiOperation(value = "根据项目Id删除项目")
    public R removeId(@ApiParam(name = "id",value = "项目管理id",required = true) @PathVariable String id){
        tProjectService.removeId(id);
        return R.ok();
    }

    /**
     * 分页查询所有项目信息
     * @param current 当前页
     * @param limit 当前页展示数
     * @return 查询后的分页数据
     */
    @ApiOperation(value = "分页查询")
    @GetMapping("pageProject/{current}/{limit}")
    public R pageListProject(
            @ApiParam(name = "current",value = "当前页",required = true)
            @PathVariable long current,
            @ApiParam(name = "limit",value = "当前页面展示数",required = true)
            @PathVariable long limit
    ){
        HashMap<String,Object> hashMap = tProjectService.pageListProject(current,limit);
        return R.ok().data(hashMap);
    }

    /**
     * 条件组合分页查询项目信息
     * @param current 当前页
     * @param limit 当前页面展示数
     * @param projectQUery 条件查询对象
     * @return 条件组合分页项目信息
     */
    @ApiOperation("条件组合分页查询项目信息接口")
    @PostMapping("pageProjectCondition/{current}/{limit}")
    public R pageProjectCondition(
            @ApiParam(name = "current",value = "当前页",required = true,defaultValue = "1")
            @PathVariable long current,
            @ApiParam(name = "limit",value = "当前页展示数",required = true,defaultValue = "5")
            @PathVariable long limit,
                @ApiParam(value = "组合条件查询对象",examples = @Example({
                        @ExampleProperty(
                                value = "{'title':'haha','testType':'1',‘begin’:'2021-05-01','end':'2021-05-08'}",
                                mediaType = "application/json")
                }))
            @RequestBody(required = false) ProjectQUery projectQUery
    ){
        HashMap<String,Object> hashMap = tProjectService.pageProjectCondition(current,limit,projectQUery);

        return R.ok().data(hashMap);
    }

    /**
     * 添加项目
     * @param tProject 项目实体类
     * @return 添加成功或失败信息
     */
    @ApiOperation("添加项目接口")
    @PostMapping("addProject")
    public R addProject(@RequestBody(required = true) TProject tProject){
        tProjectService.addProject(tProject);
        return R.ok().message("添加成功");
    }

    /**
     * 根据项目id查询项目信息
     * @param id 项目id
     * @return 项目信息
     */
    @ApiOperation("根据项目id查询项目信息")
    @GetMapping("getProject/{id}")
    public R getProjectById(@ApiParam(name = "id",value = "项目id",required = true) @PathVariable String id){
        TProject tp = tProjectService.getProjectById(id);
        return R.ok().data("item",tp);
    }

    /**
     * 更新项目信息
     * @param tProject 项目实体类
     * @return 添加成功或失败
     */
    @ApiOperation("更新项目信息")
    @PostMapping("updateProject")
    public R updateProject(@ApiParam(name = "tProject",value = "项目信息",required = true)@RequestBody TProject tProject){
        tProjectService.updateProject(tProject);

        return R.ok().message("更新成功");
    }

    /**
     * 条件分页查询项目信息，并统计关联接口总数和用例总数接口
     * @param current 当前页
     * @param limit 当前页展示数
     * @param projectQUery 查询条件对象
     * @return 查询数据《hashmap》
     */
    @ApiOperation("条件分页查询项目信息，并统计关联接口总数和用例总数")
    @PostMapping("pageProject/{current}/{limit}")
    public  R pageProject(
            @ApiParam(name = "current",value = "当前页",defaultValue = "1",required = true)@PathVariable long current,
            @ApiParam(name = "limit",value = "当前页展示数",defaultValue = "10",required = true)@PathVariable long limit,
            @ApiParam(name = "projectQUery",value = "查询条件")@RequestBody(required = false) ProjectQUery projectQUery){
        HashMap<String,Object> hashMap = tProjectService.pageProject(current,limit,projectQUery);
        return R.ok().data(hashMap);
    }
}

