package com.suki.oss.controller.admin;

import com.suki.common.CodeUtil;
import com.suki.oss.exception.MyOssException;
import com.suki.oss.service.FileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.suki.common.Result;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/admin/oss/file")
@Api(description = "阿里云文件管理")
@Slf4j
public class FileController {

    @Autowired
    FileService fileService;

    @ApiOperation("文件上传")
    @PostMapping("/upload")
    public Result upload(
            @ApiParam(value = "文件", required = true) // 中required=ture 是否是代表必须传参
            @RequestParam("file") MultipartFile file,
            @ApiParam(value = "文件夹名字", required = true)
            @RequestParam("module")String module){

        try {
            InputStream inputStream = file.getInputStream();
            String originName = file.getOriginalFilename();
            String upload = fileService.upload(inputStream, module, originName);
            Map<String,Object> map =new HashMap<>();
            map.put("url", upload);
            return Result.ok().setMessage("文件上传成功").setData(map);
        }catch (Exception e){
            log.error(e.getMessage());
            throw new MyOssException(CodeUtil.OSS_ERROR,"Oss服务器错误！请稍后再上传文件");
        }

    }

<<<<<<< HEAD
=======
    @ApiOperation("测试Nacos")
    @GetMapping("/test")
    public Result test() throws InterruptedException {
        Thread.sleep(3000);
        System.out.println("oss_test被调用");
        return Result.ok();
    }


    @ApiOperation("头像删除")
    @DeleteMapping("/delete/avatar")
    public Result deleteavatar(
            @ApiParam(value = "图片url", required = true)
            @RequestBody String url){
        fileService.deleteFile(url);
        return Result.ok().setMessage("文件删除成功");
    }

>>>>>>> 4ffed4f (first)
}
