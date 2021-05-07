package com.helinfengxs.projectservice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.helinfengxs.commonutils.R;
import com.helinfengxs.projectservice.entity.TProject;
import com.helinfengxs.projectservice.service.TProjectService;
import com.helinfengxs.projectservice.vo.ProjectQUery;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

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
@Api(description = "项目管理")
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
        List<TProject> list = tProjectService.list(null);
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
        boolean flag = tProjectService.removeById(id);
        if (!flag){
            return R.error();
        }
        return R.ok();
    }

    /**
     * 分页查询所有项目信息
     * @param current 当前页
     * @param limit 当前页展示数
     * @return 查询后的分页数据
     */
    @ApiOperation(value = "分页cha")
    @GetMapping("pageProject/{current}/{limit}")
    public R pageListProject(
            @ApiParam(name = "current",value = "当前页",required = true)
            @PathVariable long current,
            @ApiParam(name = "limit",value = "当前页面展示数",required = true)
            @PathVariable long limit
    ){

        Page<TProject> page = new Page<>(current,limit);
        IPage<TProject> listProject = tProjectService.page(page, null);
        long total = listProject.getTotal();
        long cut = listProject.getCurrent();
        long pages = listProject.getPages();
        List<TProject> records = listProject.getRecords();

        return R.ok().data("total",total).data("current",cut).data("pages",pages).data("rows",records);


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
        Page<TProject> page = new Page<>(current,limit);
        QueryWrapper<TProject> wrapper = new QueryWrapper<>();
        String title = projectQUery.getTitle();
        Integer testType = projectQUery.getTestType();
        String begin = projectQUery.getBegin();
        String end = projectQUery.getEnd();

        if(!StringUtils.isEmpty(title)){
            wrapper.like("title",title);
        }
        if(!StringUtils.isEmpty(testType)){
            wrapper.eq("test_type",testType);
        }
        if(!StringUtils.isEmpty(begin)){
            wrapper.ge("gmt_create",begin);
        }
        if(!StringUtils.isEmpty(end)){
            wrapper.le("gmt_create",end);
        }

        IPage<TProject> listPage = tProjectService.page(page, wrapper);
        List<TProject> records = listPage.getRecords();
        long pages = listPage.getPages();
        long cut = listPage.getCurrent();
        long total = listPage.getTotal();
        return R.ok().data("total",total).data("current",cut).data("pages",pages).data("rows",records);
    }

    /**
     * 添加项目
     * @param tProject 项目实体类
     * @return 添加成功与失败信息
     */
    @ApiOperation("添加项目接口")
    @PostMapping("addProject")
    public R addProject(@RequestBody(required = true) TProject tProject){
        boolean save = tProjectService.save(tProject);
        if(!save){
            return  R.error().message("添加失败");
        }
        return R.ok().message("添加成功");
    }
}

