package com.example.appwarehouse.pload;

import lombok.Data;

@Data
public class OutputDTO {

    private Integer warehouseId;
    private Integer clientId;
    private Integer currencyId;
    private String factureNumber;
    private String code;
}
