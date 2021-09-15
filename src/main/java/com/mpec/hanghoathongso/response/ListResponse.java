package com.mpec.hanghoathongso.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ListResponse<T> extends MyResponse {
    private T datas;
}
