package com.suki.teacher.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.suki.teacher.entity.EduSubject;
import com.suki.teacher.entity.vo.EduSubjectVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 课程科目 Mapper 接口
 * </p>
 *
 * @author suki
 * @since 2021-11-21
 */
@Mapper
@Repository
public interface EduSubjectMapper extends BaseMapper<EduSubject> {

    List<EduSubjectVo> selectNestedListByParentid(@Param("parentId") String parentId);
}
