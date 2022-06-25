package com.example.appwarehouse.service;

import com.example.appwarehouse.entity.User;
import com.example.appwarehouse.entity.Warehouse;
import com.example.appwarehouse.pload.Result;
import com.example.appwarehouse.pload.UserDTO;
import com.example.appwarehouse.repository.UserRepository;
import com.example.appwarehouse.repository.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    WarehouseRepository warehouseRepository;

    public Result addUser(UserDTO userDTO) {

        User user = new User(userDTO.getFirstName(), userDTO.getLastName(),
                userDTO.getPhoneNumber(), userDTO.getCode(), userDTO.getPassword());

        Set<Warehouse> warehouses = new HashSet<>();
        for (Integer warehouseId : userDTO.getWarehouses()) {
            Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(warehouseId);
            if (!optionalWarehouse.isPresent())
                return new Result("You have not permission for this warehouse id: " + warehouseId, false);
            warehouses.add(optionalWarehouse.get());
        }

        user.setWarehouses(warehouses);
        userRepository.save(user);
        return new Result("Successfully added", true, user);
    }

    public Result editUser(Integer userId, UserDTO userDTO) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (!optionalUser.isPresent())
            return new Result("User not found", false);
        User user = optionalUser.get();

        user.setFistName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setPhoneNumber(userDTO.getPhoneNumber());
        user.setCode(userDTO.getCode());
        user.setPassword(userDTO.getPassword());
        user.setActive(userDTO.getActive());

        Set<Warehouse> warehouses = null;
        for (Integer warehouseId : userDTO.getWarehouses()) {
            Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(warehouseId);
            if (!optionalWarehouse.isPresent())
                return new Result("You have not permission for this warehouse id: " + warehouseId, false);
            warehouses.add(optionalWarehouse.get());
        }
        user.setWarehouses(warehouses);

        userRepository.save(user);
        return new Result("Successfully edited", true, user);

    }

    public Result getAllUsers() {
        List<User> all = userRepository.findAll();
        return new Result("Here is info about all users", true, all);
    }

    public Result getUser(Integer userid) {
        Optional<User> optionalUser = userRepository.findById(userid);
        if (!optionalUser.isPresent())
            return new Result("Such user not found", false);

        User user = optionalUser.get();
        return new Result("Info about user id: " + user.getId(), true, user);
    }

    public Result deleteUser(Integer userId) {
        userRepository.deleteById(userId);
        return new Result("Successfully delete", true);
    }

}
