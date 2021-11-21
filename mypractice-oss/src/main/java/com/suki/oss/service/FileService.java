package com.suki.oss.service;

import java.io.InputStream;

public interface FileService {

    // 用流式上传 module是指定上传的目录名字  文件原始名字
    String upload(InputStream is,String module,String originalFileName);

    void deleteFile(String url);

}
