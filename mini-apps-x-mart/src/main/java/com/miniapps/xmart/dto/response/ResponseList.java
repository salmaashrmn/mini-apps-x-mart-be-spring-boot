package com.miniapps.xmart.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseList<T> {
    private Integer status;
    private String message;
    private List<T> data;
}
