package com.helinfenxs.projectservice.controller;


import com.helinfenxs.projectservice.entity.TProject;
import com.helinfenxs.projectservice.service.TProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

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
@RequestMapping("/projectservice/t-project")
public class TProjectController {

    @Autowired
    private TProjectService tProjectService;

    @GetMapping("findAll")
    public List<TProject> findAll(){
        List<TProject> list = tProjectService.list(null);
        return list;
    }
}

