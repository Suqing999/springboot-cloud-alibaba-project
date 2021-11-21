package com.suki.teacher.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.suki.teacher.entity.excel.ExcelSubjectData;

import java.util.ArrayList;
import java.util.List;

public class ExcelListener extends AnalysisEventListener<ExcelSubjectData> {

    @Override
    public void invoke(ExcelSubjectData data, AnalysisContext analysisContext) {
        System.out.println("调用");
        String levelone = data.getLevelone(); //一级标题
        String leveltwo = data.getLeveltwo(); //二级标题

        System.out.println(levelone+"  "+leveltwo);

    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        System.out.println("解析完成");
        //再存储剩余数据、
        System.out.println("不足300，则存储数据库");
    }
}
