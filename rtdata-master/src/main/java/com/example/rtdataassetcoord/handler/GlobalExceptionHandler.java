package com.example.rtdataassetcoord.handler;


import com.example.rtdataassetcoord.common.BusException;
import com.example.rtdataassetcoord.common.R;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {



    @ExceptionHandler(Exception.class)
    public R handleRuntimeException(Exception e)
    {
        return new R().Error(e.getMessage());
    }

    @ExceptionHandler(BusException.class)
    public R handleBusException(BusException e)
    {
        return new R().Error(e.message);
    }

}
