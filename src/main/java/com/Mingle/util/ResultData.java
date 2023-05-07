package com.Mingle.util;

import lombok.Data;
import java.io.Serializable;

@Data
public class ResultData<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    private String status;
    private String msg;
    private T data;

    public ResultData(){
        this.status = "200";
        this.setMsg("OK");
    }
}
