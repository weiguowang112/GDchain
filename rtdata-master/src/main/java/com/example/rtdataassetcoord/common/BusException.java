package com.example.rtdataassetcoord.common;//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//



public class BusException extends RuntimeException {
    public Integer code;
    public String message;

    public BusException(String message) {
        super(message);
        this.code = -1;
        this.message = message;
    }

    public BusException(String message, Integer code) {
        super(message);
        this.code = code;
        this.message = message;
    }
}
