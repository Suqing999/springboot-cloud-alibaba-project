package com.suki.oss.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.CannedAccessControlList;
import com.suki.oss.service.FileService;
import com.suki.oss.util.OssProperties;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {

    @Autowired
    private OssProperties ossProperties;

    @Override
    public String upload(InputStream is, String module, String originalFileName) {
        String endpoint = ossProperties.getEndpoint();
        String accessKeyId = ossProperties.getKeyid();
        String accessKeySecret = ossProperties.getKeysecret();
        String bucketName = ossProperties.getBucketname();
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        if(!ossClient.doesBucketExist(bucketName)){
            ossClient.createBucket(bucketName); //创建
            ossClient.setBucketAcl(bucketName, CannedAccessControlList.PublicRead); //设置权限
        }
        //构建访问目录 即文件路径 : avatar/2021/03/14/1.png
        //取出当前日期
        String currentTime = new DateTime().toString("yyyy/MM/dd");
        //随机命名文件名字
        String fileName = UUID.randomUUID().toString();
        //再加一个文件扩展名 (包含点符号，并且取出后面的名字)
        String substring = originalFileName.substring(originalFileName.lastIndexOf("."));

        String objectName = module+"/"+currentTime+"/"+fileName+substring;

        // 上传
        ossClient.putObject(bucketName, objectName, is);
        // 关闭OSSClient。
        ossClient.shutdown();
        //返回的是url https://mypractice1024-1.oss-cn-beijing.aliyuncs.com/avatar/1.png
        return "https://"+bucketName+"."+endpoint+"/"+objectName;
    }
    @Override
    public void deleteFile(String url) {
        //url https://mypractice1024-1.oss-cn-beijing.aliyuncs.com/avatar/1.png
        String endpoint = ossProperties.getEndpoint();
        String accessKeyId = ossProperties.getKeyid();
        String accessKeySecret = ossProperties.getKeysecret();
        String bucketName = ossProperties.getBucketname();
        // 填写文件完整路径。文件完整路径中不能包含Bucket名称。
        String host = "https://"+bucketName+"."+endpoint+"/"; //https://mypractice1024-1.oss-cn-beijing.aliyuncs.com/
        String objectName = url.substring(host.length()); //从主机名字往后截取
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        // 删除文件或目录。如果要删除目录，目录必须为空。
        ossClient.deleteObject(bucketName, objectName);
        // 关闭OSSClient。
        ossClient.shutdown();
    }
}
