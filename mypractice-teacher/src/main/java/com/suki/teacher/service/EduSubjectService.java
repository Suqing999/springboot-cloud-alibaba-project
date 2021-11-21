package com.suki.teacher.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.suki.teacher.entity.EduSubject;
import org.springframework.stereotype.Service;

import java.io.InputStream;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author suki
 * @since 2021-11-21
 */
@Service
public interface EduSubjectService extends IService<EduSubject> {
    void batchImport(InputStream inputStream);
}
