package com.example.appwarehouse.pload;

import lombok.Data;

@Data
public class InputProductDTO {

    private Integer productId;
    private Double amount;
    private Double price;
    private Integer inputId;
}
