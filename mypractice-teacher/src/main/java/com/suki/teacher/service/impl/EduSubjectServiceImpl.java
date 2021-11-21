package com.suki.teacher.service.impl;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.suki.teacher.entity.EduSubject;
import com.suki.teacher.entity.excel.ExcelSubjectData;
import com.suki.teacher.listener.ExcelListener;
import com.suki.teacher.mapper.EduSubjectMapper;
import com.suki.teacher.service.EduSubjectService;
import org.springframework.stereotype.Service;

import java.io.InputStream;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author suki
 * @since 2021-11-21
 */
@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService {

    @Override
    public void batchImport(InputStream inputStream) {

        System.out.println("diaoyong");
        System.out.println(inputStream);
        EasyExcel.read(inputStream, ExcelSubjectData.class, new ExcelListener())
                .excelType(ExcelTypeEnum.XLS)
                .sheet("me1")
                .doRead();

    }
}
