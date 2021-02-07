package com.ecse428.billmaker.controller;

import com.ecse428.billmaker.dto.*;
import com.ecse428.billmaker.model.*;
import com.ecse428.billmaker.service.BillMakerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class BillMakerController {

    @Autowired
    BillMakerService service;

    @PostMapping(value = {"/createIndividualUser","/createIndividualUser/"})
    IndividualUserDto createIndividualUser(@RequestParam("username") String username,
                                           @RequestParam("password") String password,
                                           @RequestParam("email") String email) {
        IndividualUser individualUser = service.createIndividualUser(username, password, email);
        return convertToDto(individualUser);

    }

    @PostMapping(value = {"/supervisor/{username}", "/supervisor/{username}/"})
    SupervisorUserDto createSupervisorUser(@PathVariable("username") String username,
                                           @RequestParam("password") String password,
                                           @RequestParam("email") String email) {
        // todo: check better way to represent the requestparam
        SupervisorUser supervisorUser = service.createSupervisorUser(username, password, email);
        return convertToDto(supervisorUser);
    }


    IndividualUserDto convertToDto(IndividualUser iu) {
        if (iu == null){
            return null;
        }
        return new IndividualUserDto(iu.getUsername(), iu.getPassword(), iu.getEmail());
    }

    SupervisorUserDto convertToDto(SupervisorUser su) {
        if (su == null) {
            return null;
        }

        return new SupervisorUserDto(su.getUsername(), su.getPassword(), su.getEmail());
    }
}
