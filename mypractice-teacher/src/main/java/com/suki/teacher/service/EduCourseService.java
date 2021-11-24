package com.suki.teacher.service;

import com.suki.teacher.entity.EduCourse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.suki.teacher.entity.form.CourseInfoForm;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author suki
 * @since 2021-11-24
 */
public interface EduCourseService extends IService<EduCourse> {

    String saveCourseInfo(CourseInfoForm courseInfoForm);
}
