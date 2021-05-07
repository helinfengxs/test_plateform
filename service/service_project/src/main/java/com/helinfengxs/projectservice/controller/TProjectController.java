package com.helinfengxs.projectservice.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.helinfengxs.commonutils.R;
import com.helinfengxs.projectservice.entity.TProject;
import com.helinfengxs.projectservice.service.TProjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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


    }}

