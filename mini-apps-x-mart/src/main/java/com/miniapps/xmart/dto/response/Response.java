package com.miniapps.xmart.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Response <T>{
    private Integer status;
    private String message;
    private T data;
}
