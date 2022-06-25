package com.example.appwarehouse.pload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDTO {

    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String code;
    private String password;
    private Boolean active;
    private Set<Integer> warehouses;
}
