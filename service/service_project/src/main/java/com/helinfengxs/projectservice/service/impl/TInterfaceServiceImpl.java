package com.helinfengxs.projectservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.helinfengxs.projectservice.entity.TInterface;
import com.helinfengxs.projectservice.mapper.TInterfaceMapper;
import com.helinfengxs.projectservice.service.TInterfaceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.helinfengxs.projectservice.vo.InterfaceList;
import com.helinfengxs.projectservice.vo.InterfacePageQuery;
import com.helinfengxs.servicebase.config.exceptionHandler.PlateformException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 接口信息表 服务实现类
 * </p>
 *
 * @author helinfeng
 * @since 2021-05-16
 */
@Service
public class TInterfaceServiceImpl extends ServiceImpl<TInterfaceMapper, TInterface> implements TInterfaceService {


    /**
     * 实现条件组合分页查询接口
     * @param current 当前页
     * @param limit 当前页展示数
     * @param interfacePageQuery 条件组合查询对象
     * @return 返回插件结果
     */
    @Override
    public HashMap<String, Object> pageInterface(long current, long limit, InterfacePageQuery interfacePageQuery) {
        HashMap<String, Object> finalResult = new HashMap<>();

        Page<InterfaceList> page = new Page<>(current,limit);
        IPage<InterfaceList> tInterfaceIPage = null;
//        try {
             tInterfaceIPage = baseMapper.pageInterface(page, interfacePageQuery);
//        }catch (Exception e){
//            throw new PlateformException(20001,"分页查询异常");
//        }

        finalResult.put("total",tInterfaceIPage.getTotal());
        finalResult.put("current",tInterfaceIPage.getCurrent());
        finalResult.put("limit",tInterfaceIPage.getPages());
        finalResult.put("items",tInterfaceIPage.getRecords());
        return finalResult;
    }

    /**
     * 实现根据接口id查询接口信息接口
     * @param id 接口id
     * @return 返回接口信息
     */
    @Override
    public TInterface findInterfaceById(String id) {
        if(StringUtils.isEmpty(id)){
            throw new PlateformException(20001,"接口id为空");
        }
        TInterface tInterface = null;
        try {
            tInterface   = baseMapper.selectById(id);
        }catch (Exception e){
            throw new PlateformException(20001,"查询接口信息异常");
        }
        return tInterface;
    }

    /**
     * 实现根据接口id删除接口信息接口
     * @param id 接口id
     */
    @Override
    public void deleteInterById(String id) {
        if(StringUtils.isEmpty(id)){
            throw new PlateformException(20001,"接口id为空");
        }
        int flag = baseMapper.deleteById(id);
        if(flag < 0){
            throw new PlateformException(20001,"删除接口信息异常");
        }
    }

    /**
     * 实现添加接口信息接口
     * @param tInterface 接口信息实体类
     */
    @Override
    public void addInterface(TInterface tInterface) {
        if(tInterface == null){
            throw new PlateformException(20001,"接口信息为空");
        }
        String interfaceName = tInterface.getInterfaceName();
        String interfaceAddress = tInterface.getInterfaceAddress();
        String projectId = tInterface.getProjectId();
        if(StringUtils.isEmpty(interfaceName)){
            throw new PlateformException(20001,"接口名称为空");
        }
        if(StringUtils.isEmpty(interfaceAddress)){
            throw  new PlateformException(20001,"接口地址为空");
        }
        if(StringUtils.isEmpty(projectId)){
            throw new PlateformException(20001,"项目id为空");
        }
        QueryWrapper<TInterface> wrapper = new QueryWrapper<>();
        wrapper.eq("interface_name",interfaceName);
        wrapper.eq("interface_address",interfaceAddress);
        Integer integerFlag = baseMapper.selectCount(wrapper);
        if(integerFlag > 0){
            throw new PlateformException(20001,"接口名称或者地址重复");
        }
        Integer flag = null;
        try {
            flag = baseMapper.insert(tInterface);
        }catch (Exception e){
            throw new PlateformException(20001,"添加接口异常");
        }
        if(flag < 0){
            throw new PlateformException(20001,"添加接口失败");
        }
    }

    /**
     * 实现更新接口信息接口
     * @param tInterface 接口信息实体类
     */
    @Override
    public void updateInterfaceInfo(TInterface tInterface) {
        if(tInterface == null){
            throw  new PlateformException(20001,"项目信息为空");
        }
        int flag = baseMapper.updateById(tInterface);
        if(flag < 0){
            throw new PlateformException(20001,"更新接口信息失败");
        }
    }

    @Override
    public void deletePartInterface(List<String> ids) {
        if(ids == null){
            throw new PlateformException(20001,"接口id为空");
        }
        int deleteBatchIds = baseMapper.deleteBatchIds(ids);
        if(deleteBatchIds < 0){
            throw new PlateformException(20001,"批量删除异常");
        }
    }
}
