package com.suki.teacher.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.suki.teacher.entity.EduSubject;
import com.suki.teacher.entity.excel.ExcelSubjectData;
import com.suki.teacher.mapper.EduSubjectMapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExcelListener extends AnalysisEventListener<ExcelSubjectData> {

    private EduSubjectMapper eduSubjectMapper;

    public ExcelListener() {
    }

    public ExcelListener(EduSubjectMapper eduSubjectMapper) {
        this.eduSubjectMapper = eduSubjectMapper;
    }

    @Override
    public void invoke(ExcelSubjectData data, AnalysisContext analysisContext) {
        System.out.println("调用");
        String levelone = data.getLevelone(); //一级标题
        String leveltwo = data.getLeveltwo(); //二级标题

        System.out.println(levelone+"  "+leveltwo);

        //组装数据，存入数据库
        //首先组装一级类别
        EduSubject eduSubject = new EduSubject();
        eduSubject.setParentId("0");
        eduSubject.setTitle(levelone);
        eduSubject.setSort(0);
        //要没有重复的才存入
        if(!isHaveSubject(levelone)){
            eduSubjectMapper.insert(eduSubject);
        }


        //先查出父亲id
        QueryWrapper<EduSubject> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("title", levelone);
        EduSubject sub = eduSubjectMapper.selectOne(queryWrapper);
        String parentId = sub.getId();
        //二级类别
        EduSubject eduSubject2 = new EduSubject();
        eduSubject2.setParentId(parentId);
        eduSubject2.setTitle(leveltwo);

        //要没有重复的才存入
        if(!isHaveTwoSubject(leveltwo,parentId)){
            eduSubjectMapper.insert(eduSubject2);
        }



    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        System.out.println("解析完成");
        //再存储剩余数据、
        System.out.println("不足300，则存储数据库");
    }

    //辅助方法  ----- >  看是否存在
    private boolean isHaveSubject(String name){
       Map<String,Object> map = new HashMap<>();
       map.put("title", name);
       List<EduSubject> eduSubjects = eduSubjectMapper.selectByMap(map);
       return eduSubjects.size()==0?false:true;
    }

    //根据一级和二级看是否重复
    private boolean isHaveTwoSubject(String title,String parentId){
        QueryWrapper<EduSubject> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("title", title);
        queryWrapper.eq("parent_id", parentId); //二级分类
        List<EduSubject> eduSubjects = eduSubjectMapper.selectList(queryWrapper);
        return eduSubjects.size()==0?false:true;
    }

}
