package com.mpec.hanghoathongso.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MyResponse {
    private Integer code;
    private String message;

    public void setResult(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public void success() {
        this.code = 0;
        this.message = "success";
    }
}
