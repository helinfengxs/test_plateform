package com.helinfengxs.projectservice.service;

import com.helinfengxs.projectservice.entity.TProject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.helinfengxs.projectservice.vo.ProjectQUery;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author helinfeng
 * @since 2021-05-06
 */
public interface TProjectService extends IService<TProject> {
    /**
     * 查询所有项目信息接口
     * @return 所有项目信息列表
     */
    List<TProject> findAll();

    /**
     * 根据项目id删除项目信息接口
     * @param id 项目id
     */
    void removeId(String id);

    /**
     * 分页查询项目信息接口
     * @param current 当前页
     * @param limit 当前页展示数
     * @return 分页查询后的数据并存放HashMap
     */
    HashMap<String, Object> pageListProject(long current, long limit);

    /**
     * 条件组合分页查询项目信息接口
     * @param current 当前页
     * @param limit 当前页展示数
     * @param projectQUery 条件 组合查询对象
     * @return 返回查询后的数据
     */
    HashMap<String, Object> pageProjectCondition(long current, long limit, ProjectQUery projectQUery);

    /**
     * 添加项目接口
     * @param tProject 项目信息
     */
    void addProject(TProject tProject);

    /**
     * 根据项目id查询项目信息接口
     * @param id 项目id
     * @return 返回该id项目信息
     */
    TProject getProjectById(String id);

    /**
     * 更新项目信息接口
     * @param tProject 项目信息
     */
    void updateProject(TProject tProject);
}
