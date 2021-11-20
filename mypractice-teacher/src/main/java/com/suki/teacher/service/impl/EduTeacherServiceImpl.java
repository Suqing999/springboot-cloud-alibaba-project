package com.suki.teacher.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.suki.teacher.entity.EduTeacher;
import com.suki.teacher.entity.TeacherQuery;
import com.suki.teacher.mapper.EduTeacherMapper;
import com.suki.teacher.service.EduTeacherService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 讲师 服务实现类
 * </p>
 *
 * @author suki
 * @since 2021-11-16
 */
@Service
public class EduTeacherServiceImpl extends ServiceImpl<EduTeacherMapper, EduTeacher> implements EduTeacherService {

    @Override
    public void pageQuery(Page<EduTeacher> pageParam, TeacherQuery teacherQuery) {
        QueryWrapper<EduTeacher> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByAsc("sort");

        if (teacherQuery == null){
            baseMapper.selectPage(pageParam, queryWrapper);
            return;
        }

        String name = teacherQuery.getName();
        Integer level = teacherQuery.getLevel();
        String begin = teacherQuery.getBegin();
        String end = teacherQuery.getEnd();

        if (name!=null) {
            queryWrapper.like("name", name);
        }

        if (level!=null) {
            queryWrapper.eq("level", level);
        }

        if (begin!=null) {
            queryWrapper.ge("gmt_create", begin);
        }

        if (end!=null) {
            queryWrapper.le("gmt_create", end);
        }
        baseMapper.selectPage(pageParam, queryWrapper);
    }

    @Override
    public List<Map<String, Object>> selectNameList(String key) {
        QueryWrapper<EduTeacher> qw = new QueryWrapper<>();
        qw.select("name");//查询name列
        //sql语句 select name from edu_teacher where name like "周%"
        qw.likeRight("name", key);
        List<Map<String, Object>> maps = baseMapper.selectMaps(qw);

        return maps;
    }
}
