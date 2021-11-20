package com.suki.oss.exception;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "Oss后台异常")
public class MyOssException extends RuntimeException{
        @ApiModelProperty(value = "状态码")
        private Integer code;
        @ApiModelProperty(value = "异常消息")
        private String msg;
}
