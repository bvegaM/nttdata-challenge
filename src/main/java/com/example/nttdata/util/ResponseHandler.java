package com.example.nttdata.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class ResponseHandler {

    public static ResponseEntity<Object> generateOperation(String message, HttpStatus status, Object data){
        Map<String, Object> map = new HashMap<>();
        map.put("message",message);
        map.put("status",status.value());
        map.put("data",data);

        return new ResponseEntity<>(map,status);
    }
}
