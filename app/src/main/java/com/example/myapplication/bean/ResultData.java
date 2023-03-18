package com.example.myapplication.bean;

/**
 * @Description:接口返回数据处理
 * @Author: dick
 * @CreateDate: 2023/3/13
 * @Version:
 */
public  class ResultData<T> {
    private int code;
    private String msg;
    private T data;
    private Object arrayList;


    public Object getArrayList() {
        return arrayList;
    }

    public void setArrayList(Object arrayList) {
        this.arrayList = arrayList;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResultData{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                ", arrayList='" + arrayList + '\'' +
                '}';
    }
}


