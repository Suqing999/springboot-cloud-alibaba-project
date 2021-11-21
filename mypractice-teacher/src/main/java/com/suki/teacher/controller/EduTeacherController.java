package com.suki.teacher.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.suki.common.CodeUtil;
import com.suki.common.Result;
import com.suki.teacher.entity.EduTeacher;
import com.suki.teacher.entity.TeacherQuery;
import com.suki.teacher.exception.MyException;
import com.suki.teacher.feign.OssFileService;
import com.suki.teacher.service.EduSubjectService;
import com.suki.teacher.service.EduTeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * 讲师前端控制器
 * @author suki
 * @since 2021-11-16
 */

// 参数上 ：@ApiParam(name = "id", value = "讲师ID", required = true)
@Api(description="讲师管理")
@RestController
@RequestMapping("/teacher")
@CrossOrigin //允许其他的服务器通过ajax访问该服务器上的接口
public class EduTeacherController {

    @Autowired
    EduTeacherService eduTeacherService;

    @Autowired
    EduSubjectService eduSubjectService;

    @Autowired
    OssFileService ossFileService;

    @ApiOperation(value = "Feign测试")
    @GetMapping("/haha")
    public void testFeign(){
        ossFileService.test();
    }

    @ApiOperation(value = "并发测试")
    @GetMapping("/test_high")
    public void testHigh(){
        System.out.println("high 高并发");
    }



    @ApiOperation(value = "获取所有讲师")
    @GetMapping("/list")
    public Result eduList(){
        try {
            List<EduTeacher> list = eduTeacherService.list(null);
            Map<String,Object> map =new HashMap<>();
            map.put("items", list);
            return Result.ok().setData(map);
        }catch (Exception e){
            return Result.error();
        }

    }

    @ApiOperation(value = "删除讲师")  //存在bug，删除已经删除的不会报错
    @DeleteMapping("/{id}")
    public Result deleteTeacher(
            @ApiParam(name = "id", value = "讲师ID", required = true)
            @PathVariable("id") String id){
        try {
            eduTeacherService.removeById(id);
            EduTeacher teacher = eduTeacherService.getById(id);
            eduTeacherService.removeById(id);
            if(teacher.getAvatar()==null){
                return Result.ok();
            }
            ossFileService.deleteavatar(teacher.getAvatar());
            return Result.ok();
        }catch (Exception e){
            return Result.error();
        }
    }


    @ApiOperation(value = "分页讲师")
    @GetMapping("/{page}/{limit}")
    public Result pageList(
            @ApiParam(name = "page", value = "当前页码", required = true)
            @PathVariable("page") Long page,
            @ApiParam(name = "limit", value = "每页多少条", required = true)
            @PathVariable("limit") Long limit,
            @ApiParam(name = "teacherQuery", value = "查询对象", required = false)
                    TeacherQuery teacherQuery) {
        try {
            Page<EduTeacher> pageParam = new Page<>(page, limit);
           // eduTeacherService.page(pageParam, null);
            eduTeacherService.pageQuery(pageParam, teacherQuery);
            List<EduTeacher> records = pageParam.getRecords();
            long total = pageParam.getTotal();
            Map<String,Object> map =new HashMap<>();
            map.put("rows", records);
            map.put("total", total);
            return Result.ok().setData(map);
        }catch (Exception e){
            return Result.error();
        }

    }

    @ApiOperation(value = "新增讲师")
    @PostMapping("/save")
    public Result save(
            @ApiParam(name = "teacher", value = "讲师对象", required = true)
            @RequestBody EduTeacher teacher){
        System.out.println(teacher);
        try {
            eduTeacherService.save(teacher);
            return Result.ok();
        } catch (Exception e) {
            return Result.error();
        }
    }

    @ApiOperation(value = "根据ID查询讲师")
    @GetMapping("{id}")
    public Result getById(
            @ApiParam(name = "id", value = "讲师ID", required = true)
            @PathVariable("id") String id){


            EduTeacher teacher = eduTeacherService.getById(id);

            if(teacher==null){
                System.out.println("hhhh");
                throw new MyException(CodeUtil.NULL_DATA,"没有这个老师！");
            }
        try {
            Map<String,Object> map =new HashMap<>();
            map.put("item", teacher);
            return Result.ok().setData(map);
        } catch (Exception e) {
            return Result.error();
        }
    }

    @ApiOperation(value = "根据ID修改讲师")
    @PutMapping("{id}")
    public Result updateById(
            @ApiParam(name = "id", value = "讲师ID", required = true)
            @PathVariable("id") String id,
            @ApiParam(name = "teacher", value = "讲师对象", required = true)
            @RequestBody EduTeacher teacher){
        try {
            teacher.setId(id);
            eduTeacherService.updateById(teacher);
            return Result.ok();
        } catch (Exception e) {
            return Result.error();
        }
    }

    //批量删除（根据id列表删除）
    @ApiOperation(value = "删除讲师列表")  //存在bug，删除已经删除的不会报错
    @DeleteMapping("/removelist")
    public Result deleteTeacherList(
            @ApiParam(name = "idList", value = "讲师ID列表", required = true)
            @RequestBody List<String>  idList){
        try {
            eduTeacherService.removeByIds(idList);
            return Result.ok();
        }catch (Exception e){
            return Result.error();
        }
    }




    @ApiOperation(value = "根据名字关键字查询讲师列表")
    @GetMapping("/list/name/{key}")
    public Result getByName(
            @ApiParam(name = "key", value = "讲师关键字", required = true)
            @PathVariable("key") String key){

        List<Map<String,Object>> teachers = eduTeacherService.selectNameList(key);
        Map<String,Object> map =new HashMap<>();
        map.put("nameList", teachers);
        return Result.ok().setData(map);
    }




    @ApiOperation("批量导入课程分类")
    @PostMapping("/subject/import")
    public Result batchInput(
            @ApiParam(name = "file", value = "文件", required = true)
            @RequestParam("file") MultipartFile file
    ) {
        try {
            eduSubjectService.batchImport(file.getInputStream());
            System.out.println("成功");
            return Result.ok().setMessage("导入成功");
        } catch (Exception e) {
            throw new MyException(CodeUtil.OSS_ERROR, "课程excel文件上传异常");
        }
    }

}

