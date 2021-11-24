package com.suki.teacher.mapper;

import com.suki.teacher.entity.EduCourse;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author suki
 * @since 2021-11-24
 */
@Mapper
@Repository
public interface EduCourseMapper extends BaseMapper<EduCourse> {

}
