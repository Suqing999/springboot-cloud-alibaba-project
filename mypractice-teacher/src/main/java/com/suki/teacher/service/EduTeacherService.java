package com.suki.teacher.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.suki.teacher.entity.EduTeacher;
import com.baomidou.mybatisplus.extension.service.IService;
import com.suki.teacher.entity.TeacherQuery;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author suki
 * @since 2021-11-16
 */
public interface EduTeacherService extends IService<EduTeacher> {
    /**
     *  根据多个条件查询讲师列表
     * @param pageParam
     * @param teacherQuery
     */
    void pageQuery(Page<EduTeacher> pageParam, TeacherQuery teacherQuery);

    List<Map<String, Object>> selectNameList(String key);
}
