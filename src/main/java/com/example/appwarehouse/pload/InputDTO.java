package com.example.appwarehouse.pload;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class InputDTO {

    private Integer warehouseId;
    private Integer supplierId;
    private Integer currencyId;
    private String factureNumber;
    private String code;
}
