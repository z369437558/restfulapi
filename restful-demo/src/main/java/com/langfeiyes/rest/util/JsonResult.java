package com.langfeiyes.rest.util;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//统一的接口响应对象
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class JsonResult {
    private int code;           //请求操作完之后返回状态码： 操作成功200， 操作失败：500，没有登录403
    private String msg;         //请求操作之后返回信息
    private Object user;        //请求响应数据
    private String cause;
    private Object recipe;
    public JsonResult(int code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.user = data;
    }
    public JsonResult(int code, String msg, Object data,String cause) {
        this.code = code;
        this.msg = msg;
        this.user = data;
        this.cause = cause;
    }

//    public static JsonResult error(String msg){
//        return new JsonResult(500, msg, null);
//    }
//    public static JsonResult error(String msg, Object data){
//        return new JsonResult(500, msg, data);
//    }
//    public static JsonResult success(){
//        return new JsonResult(200, "操作成功", null);
//    }
//    public static JsonResult success(Object data){
//        return new JsonResult(200, "操作成功", data);
//    }

}
