package com.pzy.util;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author Nice
 * @Date 2021/7/2 16:18
 * 统一结果封装
 */

@Data
public class Result implements Serializable {

    private int code;   //200是正常 非200为异常
    private String msg;
    private Object data;


    public static Result succ(String msg){
        return succ(200,msg,null);
    }

    public static Result succ(Object data){
        return succ(200,"true",data);
    }

    public static Result succ(int code,String msg,Object data){
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        result.setData(data);
        return result;
    }


    public static Result fail(String msg){
        return fail(400,msg,null);
    }

    public static Result fail(String msg,Object data){
        return fail(400,msg,data);
    }

    public static Result fail(int code,String msg,Object data){
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        result.setData(data);
        return result;
    }

}
