package com.suki.teacher.service.impl;

import com.suki.teacher.entity.EduCourse;
import com.suki.teacher.entity.EduCourseDescription;
import com.suki.teacher.entity.form.CourseInfoForm;
import com.suki.teacher.mapper.EduCourseDescriptionMapper;
import com.suki.teacher.mapper.EduCourseMapper;
import com.suki.teacher.service.EduCourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author suki
 * @since 2021-11-24
 */
@Service
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse> implements EduCourseService {
    @Autowired
    private EduCourseDescriptionMapper eduCourseDescriptionMapper;
    @Autowired
    private EduCourseMapper eduCourseMapper;

    @Transactional(rollbackFor = Exception.class) //所有异常都回滚
    @Override
    public String saveCourseInfo(CourseInfoForm courseInfoForm) {
        System.out.println("service进入");
        //做两件事 1 、 保存Course
        EduCourse eduCourse = new EduCourse();
        //利用BeanUtils工具的copyProperties   或者就是 set/get方法
        BeanUtils.copyProperties(courseInfoForm, eduCourse); //源--->目的  同名的
        eduCourse.setStatus(EduCourse.COURSE_DRAFT);//初始状态是未发布
        System.out.println("service进入1"+eduCourse);
        eduCourseMapper.insert(eduCourse); //存放数据库
        System.out.println("service进入"+eduCourse);
        System.out.println("service进入"+eduCourse.getId());

        //2、保存CourseDescription
        EduCourseDescription eduCourseDescription = new EduCourseDescription();
        eduCourseDescription.setDescription(courseInfoForm.getDescription());
        //需要拿到刚刚存储的course的信息
        //可以从34行存储进入的地方，拿到Course的id信息（MP是自动回填）
        eduCourseDescription.setId(eduCourse.getId());
        eduCourseDescriptionMapper.insert(eduCourseDescription);

        return eduCourse.getId();
    }
}
