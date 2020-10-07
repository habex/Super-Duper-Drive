package com.udacity.jwdnd.course1.cloudstorage.controller;


import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class CredentialController {

    private CredentialService credentialService;

    public CredentialController( CredentialService credentialService) {
        this.credentialService = credentialService;

    }

    @PostMapping(path = "/credential")
    public String postCredential(Credential credential, Model model) {
        this.credentialService.addCredential(credential);
        model.addAttribute("credentials", this.credentialService.getCredentials());
        return "home";
    }
}
