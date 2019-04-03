package com.spring.jianyueapi.util;

import lombok.Data;

@Data
public class ResponseResult {
    private int code;
    private String msg;
    private Object data;

    public static ResponseResult error(int code,String msg){
        ResponseResult responseResult=new ResponseResult();
        responseResult.setCode(code);
        responseResult.setMsg(msg);
        return responseResult;
    }
    public static ResponseResult success(){
        ResponseResult responseResult=new ResponseResult();
        responseResult.setCode(StatusConst.SUCCESS);
        responseResult.setMsg(MsgConst.SUCCESS);
        return responseResult;
    }
    public static ResponseResult success(Object data){
        ResponseResult responseResult=new ResponseResult();
        responseResult.setCode(StatusConst.SUCCESS);
        responseResult.setMsg(MsgConst.SUCCESS);
        responseResult.setData(data);
        return responseResult;
    }
}
