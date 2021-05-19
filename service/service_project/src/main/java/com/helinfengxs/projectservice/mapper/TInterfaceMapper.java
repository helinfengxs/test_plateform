package com.helinfengxs.projectservice.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.helinfengxs.projectservice.entity.TInterface;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.helinfengxs.projectservice.vo.InterfaceList;
import com.helinfengxs.projectservice.vo.InterfacePageQuery;
import com.helinfengxs.projectservice.vo.ProjectList;
import com.helinfengxs.projectservice.vo.ProjectQUery;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 接口信息表 Mapper 接口
 * </p>
 *
 * @author helinfeng
 * @since 2021-05-16
 */
public interface TInterfaceMapper extends BaseMapper<TInterface> {
    IPage<InterfaceList> pageInterface(IPage<InterfaceList> page, @Param("iter") InterfacePageQuery interfacePageQuery);
}
