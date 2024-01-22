package com.example.rtdataassetcoord.common;


import java.io.Serializable;

public class R<T> implements Serializable {
    private static final long serialVersionUID = -7367235882616638542L;
    public boolean success;
    public String message;
    public int code;
    public T data;

    public R() {
    }

    public R<T> Error(String message) {
        this.success = false;
        this.code = -1;
        this.message = message;
        return this;
    }

    public R<T> Success(T result) {
        this.success = true;
        this.code = 0;
        this.message = "";
        this.data = result;
        return this;
    }

    public R<T> Success() {
        this.success = true;
        this.code = 0;
        this.message = "";
        this.data = null;
        return this;
    }
}
