package com.Mingle.util;

import lombok.Data;
import java.io.Serializable;

@Data
public class ResultData<T> implements Serializable {
    private String code;
    private String msg;
    private T data;

    public ResultData() {
    }

    public ResultData(T data) {
        this.data = data;
    }

    public static ResultData success() {
        ResultData result = new ResultData<>();
        result.setCode("0");
        result.setMsg("成功");
        return result;
    }

    public static <T> ResultData<T> success(T data) {
        ResultData<T> result = new ResultData<>(data);
        result.setCode("0");
        result.setMsg("成功");
        return result;
    }

    public static <T> ResultData<T> success(T data,String msg) {
        ResultData<T> result = new ResultData<>(data);
        result.setCode("0");
        result.setMsg(msg);
        return result;
    }

    public static ResultData error(String code, String msg) {
        ResultData result = new ResultData();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }
}
