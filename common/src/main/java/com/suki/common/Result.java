package com.suki.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.HashMap;
import java.util.Map;

@Data
@NoArgsConstructor
@ApiModel("全局返回结果")
@Accessors(chain = true)  // 实现链式编程
public class Result {
    @ApiModelProperty(value = "是否成功")
    private boolean success;

    @ApiModelProperty(value = "返回码")
    private Integer code;

    @ApiModelProperty(value = "返回消息")
    private String message;

    @ApiModelProperty(value = "返回数据")
    private Map<String, Object> data = new HashMap<String, Object>();


    public static Result ok(){
        Result r = new Result();
        r.setSuccess(true);
        r.setCode(CodeUtil.OK);
        r.setMessage("成功");
        return r;
    }

    public static Result error(){
        Result r = new Result();
        r.setSuccess(false);
        r.setCode(CodeUtil.ERROR);
        r.setMessage("失败");
        return r;
    }


}
