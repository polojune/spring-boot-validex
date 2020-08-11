package com.cos.validex01;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class RespDto<T> {
    private int statusCode; // 1,2,3...
    private String msg;
    private T data;
}
