package com.suki.teacher.feign.fallback;

import com.suki.common.Result;
import com.suki.teacher.feign.OssFileService;
import org.springframework.stereotype.Component;

@Component
public class OssFileServiceFallback implements OssFileService {

    @Override
    public Result test() {
        return Result.error();
    }

    @Override
    public Result deleteavatar(String url) {
        System.out.println("开启熔断保护");
        return Result.error();
    }
}
