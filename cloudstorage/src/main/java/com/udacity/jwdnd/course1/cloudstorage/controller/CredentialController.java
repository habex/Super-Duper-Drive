package com.udacity.jwdnd.course1.cloudstorage.controller;


import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.services.*;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/home/credential")
public class CredentialController {

    private CredentialService credentialService;
    private UserService userService;
    private HomeController homeController;
    private String message = "Credentials list";

    public CredentialController(CredentialService credentialService, UserService userService, HomeController homeController) {
        this.credentialService = credentialService;
        this.userService = userService;
        this.homeController = homeController;
    }

    @PostMapping
    public String postCredential(Authentication authentication, Credential credential, Model model) {

        Integer userId = userService.getUserByName(authentication.getName()).getUserId();

        if(credential.getUserId() ==null){
            credential.setUserId(userId);
        }
        this.credentialService.addCredential(credential);
        message = "Credential"+" successfully "+ HomeController.status +" !";
        homeController.addAttributes(model,userId,"credentials",message);

        return "home";
    }

    @GetMapping(value = "/{credentialId}")
    @ResponseBody
    public Credential getCredential(Authentication authentication,@PathVariable(name = "credentialId") String credentialID ) {
        Integer credentialId = Integer.parseInt(credentialID);
        return credentialService.getCredential(credentialId);
    }

    @GetMapping("/delete/{credentialId}")
    public String deleteNote(Authentication authentication, @PathVariable Integer credentialId, Model model) {
        Integer userId = userService.getUserByName(authentication.getName()).getUserId();
        int result = credentialService.delete(credentialId);

        if (result >= 1) {
            message="Credential successfully deleted!";
        } else {
            message="Deleting the Credential was unsuccessfully!";
        }
        homeController.addAttributes(model,userId,"credentials",message);

        return "home";
    }


}
