package com.mmall.common;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.io.Serializable;

/**
 * 定义一个泛型，实现序列化接口
 * Created by WavyPeng on 2018/4/20.
 */
//保证序列化Json时，如果是null的对象，key也会消失
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class ServerResponse<T> implements Serializable{
    private int status;
    private String msg;
    private T data;  //泛型的优势：在返回时可以指定泛型的内容；也可以不指定泛型中的强制类型

    private ServerResponse(int status){
        this.status = status;
    }
    private ServerResponse(int status,T data){
        this.status = status;
        this.data = data;
    }
    private ServerResponse(int status,String msg){
        this.status = status;
        this.msg = msg;
    }
    private ServerResponse(int status,String msg,T data){
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    /**
     * 响应成功
     * @return
     */
    @JsonIgnore   //使之不在Json序列化结果当中
    public boolean isSuccess(){
        return this.status ==ResponseCode.SUCCESS.getCode();
    }

    public int getStatus(){
        return status;
    }
    public T getData(){
        return data;
    }
    public String getMsg(){
        return msg;
    }

    /**
     * 创建成功服务器响应
     * @param <T>
     * @return
     */
    public static <T> ServerResponse<T> createBySuccess(){
        return new ServerResponse<T>(ResponseCode.SUCCESS.getCode());
    }

    /**
     * 创建成功服务器响应并返回文本
     * @param msg
     * @param <T>
     * @return
     */
    public static <T> ServerResponse<T> createBySuccessMessage(String msg){
        return new ServerResponse<T>(ResponseCode.SUCCESS.getCode(),msg);
    }

    /**
     * 创建成功服务器响应并返回数据
     * @param data
     * @param <T>
     * @return
     */
    public static <T> ServerResponse<T> createBySuccess(T data){
        return new ServerResponse<T>(ResponseCode.SUCCESS.getCode(),data);
    }

    /**
     * 创建成功服务器响应并返回文本和数据
     * @param msg
     * @param data
     * @param <T>
     * @return
     */
    public static <T> ServerResponse<T> createBySuccess(String msg,T data){
        return new ServerResponse<T>(ResponseCode.SUCCESS.getCode(),msg,data);
    }

    /**
     * 创建失败服务器响应
     * @param <T>
     * @return
     */
    public static <T> ServerResponse<T> createByError(){
        return new ServerResponse<T>(ResponseCode.ERROR.getCode(),ResponseCode.ERROR.getDesc());
    }

    /**
     * 创建失败服务器响应并返回错误信息
     * @param errorMessage
     * @param <T>
     * @return
     */
    public static <T> ServerResponse<T> createByErrorMessage(String errorMessage){
        return new ServerResponse<T>(ResponseCode.ERROR.getCode(),errorMessage);
    }

    /**
     * 创建失败服务器响应并返回错误代码和错误信息
     * @param errorCode
     * @param errorMessage
     * @param <T>
     * @return
     */
    public static <T> ServerResponse<T> createByErrorCodeMessage(int errorCode,String errorMessage){
        return new ServerResponse<T>(errorCode,errorMessage);
    }
}
