package com.example.appwarehouse.controller;

import com.example.appwarehouse.pload.Result;
import com.example.appwarehouse.pload.UserDTO;
import com.example.appwarehouse.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/addUser")
    public Result addUser(@RequestBody UserDTO userDTO) {
        return userService.addUser(userDTO);
    }

    @PutMapping("/editUser/{userId}")
    public Result editUser(@PathVariable Integer userId, @RequestBody UserDTO userDTO){
        return userService.editUser(userId, userDTO);
    }

    @GetMapping("/getAllUsers")
    public Result getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("/getUser/{userId}")
    public Result getUser(@PathVariable Integer userId){
        return userService.getUser(userId);
    }

    @DeleteMapping("/deleteUser/{userId}")
    public Result deleteUser(@PathVariable Integer userId){
        return userService.deleteUser(userId);
    }

}
