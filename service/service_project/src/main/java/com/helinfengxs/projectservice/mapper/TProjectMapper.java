package com.helinfengxs.projectservice.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.helinfengxs.projectservice.entity.TProject;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.helinfengxs.projectservice.vo.ProjectList;
import com.helinfengxs.projectservice.vo.ProjectQUery;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author helinfeng
 * @since 2021-05-06
 */
public interface TProjectMapper extends BaseMapper<TProject> {
    /**
     * 条件分页查询项目信息，并统计关联接口总数和用例总数sql
     * @param page 分页对象
     * @param projectQUery 查询条件对象
     * @return 分页查询数据
     */
    IPage<ProjectList> pageProject(IPage<ProjectList> page, @Param("p")  ProjectQUery projectQUery);
}
