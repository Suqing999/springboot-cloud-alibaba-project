package com.suki.teacher.service.impl;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.suki.teacher.entity.EduSubject;
import com.suki.teacher.entity.excel.ExcelSubjectData;
import com.suki.teacher.entity.vo.EduSubjectVo;
import com.suki.teacher.listener.ExcelListener;
import com.suki.teacher.mapper.EduSubjectMapper;
import com.suki.teacher.service.EduSubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.List;

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

    @Autowired
    private EduSubjectMapper eduSubjectMapper;
    @Override
    public void batchImport(InputStream inputStream) {

        System.out.println("diaoyong");
        System.out.println(inputStream);
        EasyExcel.read(inputStream, ExcelSubjectData.class, new ExcelListener(eduSubjectMapper))
                .excelType(ExcelTypeEnum.XLS)
                .sheet("me1")
                .doRead();

    }

    @Override
    public List<EduSubjectVo> nestdList() {
        //方式1  查询所有数据，内存中通过业务处理数据（查完是扁平数据   数据量大）

        //方式2  先查一级类别，遍历一级，通过一级id再查2级类别id （数据是直接组装好的，几乎没有并发）
        //采用mapper.xml 自动映射
        return baseMapper.selectNestedListByParentid("0");
    }
}
