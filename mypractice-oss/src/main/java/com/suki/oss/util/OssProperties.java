package com.suki.oss.util;

import lombok.Data;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component  //加入ioc容器 自动实例化(需要set方法读入yaml文件)
@Data
@ToString
@ConfigurationProperties(prefix = "aliyun.oss")
public class OssProperties {
    //读取配置文件 中的内容
    private String  endpoint;
    private String  keyid ;
    private String keysecret ;
    private String bucketname ;

}
