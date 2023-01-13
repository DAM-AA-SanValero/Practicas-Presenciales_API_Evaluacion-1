package com.svalero.clothshop.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorMessage {

    public int code;
    private String message;
    private Map<String, String> errors;

    public ErrorMessage(int code, String message){
        this.code= code;
        this.message = message;
    }

}
