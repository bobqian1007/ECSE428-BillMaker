package com.ecse428.billmaker.controller;

import com.ecse428.billmaker.dto.SupervisorUserDto;
import com.ecse428.billmaker.model.SupervisorUser;
import com.ecse428.billmaker.model.User;
import com.ecse428.billmaker.model.myUser;
import com.ecse428.billmaker.service.BillMakerService;
import com.ecse428.billmaker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import com.google.gson.Gson;



@CrossOrigin(origins = "*")
@RestController
public class BillMakerController {

    @Autowired
    BillMakerService service;

    @PostMapping(value = {"/supervisor/{username}", "/supervisor/{username}/"})
    SupervisorUserDto createSupervisorUser(@PathVariable("username") String username,
                                           @RequestParam("password") String password,
                                           @RequestParam("email") String email) {
        // todo: check better way to represent the requestparam
        SupervisorUser supervisorUser = service.createSupervisorUser(username, password, email);
        return convertToDto(supervisorUser);
    }


    SupervisorUserDto convertToDto(SupervisorUser su) {
        if (su == null) {
            return null;
        }

        return new SupervisorUserDto(su.getUsername(), su.getPassword(), su.getEmail());
    }


    /**
     * User Login Related
     * - rn
     */
    @Autowired
    UserService userService;

    @GetMapping("/user/list")
    public String getUserList(Model model) {
        List<myUser> userList = userService.selectMany();
        return new Gson().toJson(userList);
    }

    @GetMapping("/admin/list")
    public String getAdminList(Model model) {
        List<myUser> userList = userService.selectMany();
        return new Gson().toJson(userList);
    }
}
