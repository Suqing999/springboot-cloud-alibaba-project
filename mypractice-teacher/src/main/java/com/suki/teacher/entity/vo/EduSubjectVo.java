package com.suki.teacher.entity.vo;

import com.suki.teacher.entity.EduSubject;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class EduSubjectVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;
    private String title;
    private Integer sort;

    //做一个自关联嵌套
    private List<EduSubjectVo> children = new ArrayList<>();
}
