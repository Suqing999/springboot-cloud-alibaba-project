package com.suki.teacher.mapper;

import com.suki.teacher.entity.EduTeacher;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 讲师 Mapper 接口
 * </p>
 *
 * @author suki
 * @since 2021-11-16
 */
@Mapper
public interface EduTeacherMapper extends BaseMapper<EduTeacher> {

}
