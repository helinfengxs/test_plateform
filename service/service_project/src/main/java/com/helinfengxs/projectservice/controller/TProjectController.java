package com.helinfengxs.projectservice.controller;


import com.helinfengxs.projectservice.entity.TProject;
import com.helinfengxs.projectservice.service.TProjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("findAll")
    @ApiOperation(value = "查询所有项目列表")
    public List<TProject> findAll(){
        List<TProject> list = tProjectService.list(null);
        return list;
    }
    @DeleteMapping("{id}")
    @ApiOperation(value = "根据项目Id删除项目")
    public boolean removeId(@ApiParam(name = "id",value = "项目管理id",required = true) @PathVariable String id){
        boolean status = tProjectService.removeById(id);
        return status;
    }
}

