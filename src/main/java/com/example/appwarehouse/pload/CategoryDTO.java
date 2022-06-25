package com.example.appwarehouse.pload;

import lombok.Data;

@Data
public class CategoryDTO {
    
    private String name;
    private Integer parentId;
    private boolean active;
}
