package com.ecse428.billmaker.controller;

import com.ecse428.billmaker.dto.IndividualUserDto;
import com.ecse428.billmaker.dto.SupervisorUserDto;
import com.ecse428.billmaker.dto.CategoryDto;
import com.ecse428.billmaker.model.SupervisorUser;
import com.ecse428.billmaker.model.IndividualUser;
import com.ecse428.billmaker.model.User;
import com.ecse428.billmaker.model.myUser;
import com.ecse428.billmaker.model.Category;
import com.ecse428.billmaker.service.BillMakerService;
import com.ecse428.billmaker.service.CategoryService;
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
    

    @PostMapping(value = {"/createIndividualUser","/createIndividualUser/"})
    IndividualUserDto createIndividualUser(@RequestParam("username") String username,
                                           @RequestParam("password") String password,
                                           @RequestParam("email") String email) {
        IndividualUser individualUser = service.createIndividualUser(username, password, email);
        return convertToDto(individualUser);

    }

    IndividualUserDto convertToDto(IndividualUser iu) {
        if (iu == null){
            return null;
        }
        return new IndividualUserDto(iu.getUsername(), iu.getPassword(), iu.getEmail(),iu.getMonthlyLimit());
    }

    @PostMapping(value = {"/supervisor/{username}", "/supervisor/{username}/"})
    SupervisorUserDto createSupervisorUser(@PathVariable("username") String username,
                                           @RequestParam("password") String password,
                                           @RequestParam("email") String email) {
        // todo: check better way to represent the requestparam
        SupervisorUser supervisorUser = service.createSupervisorUser(username, password, email);
        return convertToDto(supervisorUser);
    }

    @PostMapping(value = ("/logout"))
    void logout() {
        BillMakerService.logout();
    }

    SupervisorUserDto convertToDto(SupervisorUser su) {
        if (su == null) {
            return null;
        }

        return new SupervisorUserDto(su.getUsername(), su.getPassword(), su.getEmail());
    }
    @GetMapping(value = "/individualuser/rmlt/{username}")
    public IndividualUserDto removeMonthlimit(@PathVariable("username") String username){
        IndividualUser user;
    	try {
        	user = userService.removeMonthLimit(username);
        }catch(Exception e) {
        	user = null;
        }
    	return convertToDto(user);
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

    @PostMapping("/user/changePwd")
    public IndividualUserDto changePwd(String username, String password) {
        IndividualUser user;
        try {
            user = userService.changePwd(username, password);
        }catch(Exception e) {
            user = null;
        }
        return convertToDto(user);
    }

    @Autowired
    CategoryService categoryService;

    @PostMapping("/category/changename")
    public CategoryDto changeName(String oldName, String newName) {
        Category c;        
        try {
            c = categoryService.editCategory(oldName, newName);
        } catch (Exception e) {
            c = null;
        } 
        return convertToDto(c);
    }

    CategoryDto convertToDto(Category c) {
        if (c == null) {
            return null;
        } else {
            return new CategoryDto(c);
        }
    }
}
