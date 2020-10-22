package com.udacity.jwdnd.course1.cloudstorage.controller;


import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/credential")
public class CredentialController  {

    private CredentialService credentialService;

    public CredentialController(CredentialService credentialService) {
        this.credentialService = credentialService;

    }

    @PostMapping
    public String postCredential(Credential credential, Model model) {

        if(credential.getCredentialId() == null){
            this.credentialService.addCredential(credential);
            model.addAttribute("message", "Credential added successfully !");
        }else {
            this.credentialService.update(credential);
            model.addAttribute("message", "Credential updated successfully !");
        }


        model.addAttribute("credentials", this.credentialService.findAll());
        return "home";
    }

    @GetMapping("/delete/{credentialId}")
    public String deleteNote(@PathVariable Integer credentialId, Model model) {
        int result = credentialService.delete(credentialId);

        if (result >= 1) {
            model.addAttribute("message", "Credential deleted successfully !");
        } else {
            model.addAttribute("message", "Unsuccessfully to delete the Credential !");
        }
        model.addAttribute("credentials", this.credentialService.findAll());
        return "home";
    }

    @ModelAttribute
    public void addAttributes(Model model) {
        model.addAttribute("message", "HOME PAGE!");
    }
}
