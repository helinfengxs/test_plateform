package com.helinfengxs.projectservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.helinfengxs.projectservice.entity.TProject;
import com.helinfengxs.projectservice.mapper.TProjectMapper;
import com.helinfengxs.projectservice.service.TProjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.helinfengxs.projectservice.vo.ProjectList;
import com.helinfengxs.projectservice.vo.ProjectQUery;
import com.helinfengxs.servicebase.config.exceptionHandler.PlateformException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author helinfeng
 * @since 2021-05-06
 */
@Service
public class TProjectServiceImpl extends ServiceImpl<TProjectMapper, TProject> implements TProjectService {


    /**
     * 实现查询所有项目信息接口
     * @return 所有项目信息列表
     */
    @Override
    public List<TProject> findAll() {
        QueryWrapper<TProject> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("gmt_create");
        return baseMapper.selectList(wrapper);
    }

    /**
     * 实现根据项目id删除项目信息接口
     * @param id 项目id
     */
    @Override
    public void removeId(String id) {
        int flag = baseMapper.deleteById(id);
        if (flag < 0){
            throw new PlateformException(20001,"删除失败");
        }
    }

    /**
     * 实现分页查询项目接口
     * @param current 当前页
     * @param limit 当前页展示数
     * @return 返回分页查询项目后的数据信息
     */
    @Override
    public HashMap<String, Object> pageListProject(long current, long limit) {
        Page<TProject> page = new Page<>(current,limit);
        QueryWrapper<TProject> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("gmt_create");
        HashMap<String,Object> hashMap = new HashMap<>();
        try {
            IPage<TProject> listProject = baseMapper.selectPage(page,wrapper);
            hashMap.put("total",listProject.getTotal());
            hashMap.put("current",listProject.getCurrent());
            hashMap.put("pages",listProject.getPages());
            hashMap.put("items",listProject.getRecords());
        }catch (PlateformException e){
            throw new PlateformException(20001,"查询异常");
        }

        return hashMap;
    }

    /**
     * 实现条件组合分页查询项目信息接口
     * @param current 当前页
     * @param limit 当前页展示数
     * @param projectQUery 条件 组合查询对象
     * @return 返回条件组合分页查询后的数据添加到HashMap，返回HashMap
     */
    @Override
    public HashMap<String, Object> pageProjectCondition(long current, long limit, ProjectQUery projectQUery) {
        HashMap<String,Object> hashMap = new HashMap<>();
        Page<TProject> page = new Page<>(current,limit);
        QueryWrapper<TProject> wrapper = new QueryWrapper<>();
        try {
            if(projectQUery != null){
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
            }

            wrapper.orderByDesc("gmt_create");
            IPage<TProject> listPage = baseMapper.selectPage(page, wrapper);
            hashMap.put("total",listPage.getTotal());
            hashMap.put("current",listPage.getCurrent());
            hashMap.put("pages",listPage.getPages());
            hashMap.put("items",listPage.getRecords());
        }catch (Exception e){
            throw  new PlateformException(20001,"查询异常");
        }



        return hashMap;
    }

    /**
     * 实现添加项目信息接口
     * @param tProject 项目信息
     */
    @Override
    public void addProject(TProject tProject) {

        if(!StringUtils.isEmpty(tProject.getTitle())){
            String title = tProject.getTitle().trim();
            tProject.setTitle(title);
        }else {
            throw new PlateformException(20001,"项目名称未填入");
        }
        if(StringUtils.isEmpty(tProject.getTestType())){
            throw new PlateformException(2001,"测试类型未填入");
        }
        if(!StringUtils.isEmpty(tProject.getDescribtion())){
            String describtion = tProject.getDescribtion().trim();
            tProject.setDescribtion(describtion);
        }




        int insert = baseMapper.insert(tProject);
        if(insert < 0){
            throw new PlateformException(20001,"添加项目异常");
        }
    }

    /**
     * 实现根据项目id查询项目信息接口
     * @param id 项目id
     * @return 返回该项目信息
     */
    @Override
    public TProject getProjectById(String id) {
        TProject tProject = null;
        try {
            tProject = baseMapper.selectById(id);
        }catch (Exception e){
            throw new PlateformException(20001,"查询项目异常");
        }

        return tProject;
    }

    /**
     * 实现更新项目信息接口
     * @param tProject 项目信息
     */
    @Override
    public void updateProject(TProject tProject) {
        int flag = baseMapper.update(tProject, null);
        if(flag < 0){
            throw  new PlateformException(20001,"更新项目异常");
        }
    }

    /**
     * 条件分页查询项目信息，并统计关联接口总数和用例总数接口实现
     * @param current 当前页
     * @param limit 当前页展示数
     * @param projectQUery 查询条件方法
     * @return 查询数据《hashmap》
     */
    @Override
    public HashMap<String,Object> pageProject(long current,long limit,ProjectQUery projectQUery) {
        HashMap<String,Object> hashMap = new HashMap<>();
        Page<ProjectList> page = new Page<>(current,limit);
        IPage<ProjectList> projectListIPage = baseMapper.pageProject(page,projectQUery);

        List<ProjectList> records = projectListIPage.getRecords();
        long total = projectListIPage.getTotal();
        long cut = projectListIPage.getCurrent();
        long pages = projectListIPage.getPages();
        hashMap.put("total",total);
        hashMap.put("current",cut);
        hashMap.put("pages",pages);
        hashMap.put("items",records);

        return hashMap;
    }

    /**
     * 实现根据项目名称查询项目是否存在接口
     * @param tiltle 项目名称
     */
    @Override
    public void findProjectByTitle(String tiltle) {
        String trim = tiltle.trim();
        TProject tProject = new TProject();

        tProject.setTitle(tiltle);
        QueryWrapper<TProject> wrapper = new QueryWrapper<>();
        wrapper.eq("title",trim);
        List<TProject> selectList = baseMapper.selectList(wrapper);

        if (selectList.size() > 0) {
            throw new PlateformException(20001,"项目名称重复");
        }
    }

    @Override
    @Transactional
    public void deletePartProject(List<String> ids) {
        int deleteBatchIds = baseMapper.deleteBatchIds(ids);
        if (deleteBatchIds <0){
            throw new PlateformException(20001,"删除项目失败");
        }
    }
}
