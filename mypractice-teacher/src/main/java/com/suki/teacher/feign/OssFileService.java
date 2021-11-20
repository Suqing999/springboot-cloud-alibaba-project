package com.suki.teacher.feign;

import com.suki.common.Result;
import com.suki.teacher.feign.fallback.OssFileServiceFallback;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@FeignClient(value = "mypractice-oss",fallback = OssFileServiceFallback.class)
public interface OssFileService {
    @GetMapping("/admin/oss/file/test")
    Result test();


    @DeleteMapping("/admin/oss/file/delete/avatar")
    public Result deleteavatar(String url);
}


