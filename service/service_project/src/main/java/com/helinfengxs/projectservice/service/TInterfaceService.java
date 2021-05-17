package com.helinfengxs.projectservice.service;

import com.helinfengxs.projectservice.entity.TInterface;
import com.baomidou.mybatisplus.extension.service.IService;
import com.helinfengxs.projectservice.vo.InterfacePageQuery;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 接口信息表 服务类
 * </p>
 *
 * @author helinfeng
 * @since 2021-05-16
 */
public interface TInterfaceService extends IService<TInterface> {


    /**
     * 条件组合分页查询接口
     * @param current 当前页
     * @param limit 当前页展示数
     * @param interfacePageQuery 条件组合查询对象
     * @return 返回查询结果
     */
    HashMap<String, Object> pageInterface(long current, long limit, InterfacePageQuery interfacePageQuery);

    /**
     * 根据接口id查询接口信息接口
     * @param id 接口id
     * @return 返回接口信息
     */
    TInterface findInterfaceById(String id);

    /**
     * 根据接口id删除接口信息接口
     * @param id 接口id
     */
    void deleteInterById(String id);

    /**
     * 添加接口信息接口
     * @param tInterface 接口信息实体类
     */
    void addInterface(TInterface tInterface);

    /**
     * 更新接口信息接口
     * @param tInterface 接口信息实体类
     */
    void updateInterfaceInfo(TInterface tInterface);

    /**
     * 批量删除接口信息
     * @param ids 接口id列表
     */
    void deletePartInterface(List<String> ids);
}
