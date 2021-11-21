package com.suki.teacher.entity.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

@Data
public class ExcelSubjectData {
    @ExcelProperty("一级分类")
    private String levelone;
    @ExcelProperty("二级分类")
    private String leveltwo;
}
