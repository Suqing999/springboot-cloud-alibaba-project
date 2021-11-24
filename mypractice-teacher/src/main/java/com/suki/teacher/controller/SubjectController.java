package com.suki.teacher.controller;

import com.suki.common.Result;
import com.suki.teacher.entity.vo.EduSubjectVo;
import com.suki.teacher.service.EduSubjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(description="课程管理")
@RestController
@RequestMapping("/subject")
@CrossOrigin //允许其他的服务器通过ajax访问该服务器上的接口
public class SubjectController {
    @Autowired
    EduSubjectService eduSubjectService;
    @ApiOperation(value = "嵌套列表")
    @GetMapping("/nested-list")
    public Result nestedList(){
        List<EduSubjectVo> eduSubjectVoList =  eduSubjectService.nestdList();
        Map<String,Object> map =new HashMap<>();
        map.put("items", eduSubjectVoList);
        return Result.ok().setData(map);
    }

}
