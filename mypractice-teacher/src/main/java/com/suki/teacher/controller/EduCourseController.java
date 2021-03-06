package com.suki.teacher.controller;


import com.suki.common.Result;
import com.suki.teacher.entity.form.CourseInfoForm;
import com.suki.teacher.service.EduCourseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author suki
 * @since 2021-11-24
 */
@RestController
@RequestMapping("/edu-course")
@Api(description="讲师管理")
@CrossOrigin //允许其他的服务器通过ajax访问该服务器上的接口
public class EduCourseController {

    @Autowired
    private EduCourseService eduCourseService;


    @ApiOperation("新增课程")
    @PostMapping("/save-course-info")
    public Result saveCourseInfo(
            @ApiParam(name = "courseInfoForm", value = "课程info的Json", required = true)
            @RequestBody CourseInfoForm courseInfoForm){

        //目标是返回课程的id  供后续步骤使用
        String cid = eduCourseService.saveCourseInfo(courseInfoForm);
        System.out.println(cid);
        Map<String,Object> map = new HashMap<>();
        map.put("courseID", cid);
        return Result.ok().setData(map).setMessage("保存Info成功");
    }

    @ApiOperation("根据id查找课程")
    @GetMapping("/course-id/{id}")
    public Result saveCourseInfo(
            @ApiParam(name = "id", value = "课程的id", required = true)
            @PathVariable("id") String id){
        //目标是返回课程的id  供后续步骤使用
        CourseInfoForm courseInfoForm = eduCourseService.getCourseInfoById(id);

        if(courseInfoForm!=null){
            Map<String,Object> map = new HashMap<>();
            map.put("item", courseInfoForm);
            return Result.ok().setData(map);
        }else{

            return Result.ok().setMessage("数据不存在");
        }
    }


    @ApiOperation("更新课程")
    @PostMapping("/update-course-info")
    public Result updateCourseInfo(
            @ApiParam(name = "courseInfoForm", value = "课程info的Json", required = true)
            @RequestBody CourseInfoForm courseInfoForm){

        //目标是返回课程的id  供后续步骤使用
        eduCourseService.updateCourseInfoById(courseInfoForm);

        Map<String,Object> map = new HashMap<>();
        return Result.ok().setData(map).setMessage("修改Info成功");
    }


}

