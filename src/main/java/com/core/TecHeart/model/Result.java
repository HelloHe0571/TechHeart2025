package com.core.TecHeart.model;

import lombok.NoArgsConstructor;

import java.io.Serializable;


@NoArgsConstructor
public class Result<T> implements Serializable {
    /**
     * 消息
     */
    private String message;

    /**
     * 是否操作成功
     */
    private boolean success;

    /**
     * 返回的数据主体（返回的内容）
     */
    private T data;

    /**
     * 设定结果为成功
     *
     * @param msg 消息
     */
    public void setResultSuccess(String msg) {

        this.message = msg;
        this.success = true;
        this.data = null;
    }

    /**
     * 设定结果为成功
     *
     * @param msg  消息
     * @param data 数据体
     */
    public void setResultSuccess(String msg, T data) {

        this.message = msg;
        this.success = true;
        this.data = data;
    }

    /**
     * 设定结果为失败
     *
     * @param msg 消息
     */
    public void setResultFailed(String msg) {

        this.message = msg;
        this.success = false;
        this.data = null;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
